// TransaksiRestoran.java
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TransaksiRestoran {
    private Connection conn;

    public TransaksiRestoran(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<String> getDaftarTamu() {
        ArrayList<String> tamuList = new ArrayList<>();
        try {
            String sql = "SELECT nama FROM tamu";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tamuList.add(rs.getString("nama"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tamuList;
    }

    public DefaultTableModel getDaftarMakanan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Makanan");
        model.addColumn("Harga");

        try {
            String sql = "SELECT * FROM makanan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idMakanan"),
                    rs.getString("nama"),
                    rs.getInt("harga")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public void simpanPesanan(String namaTamu, int idMakanan, int jumlah) {
        try {
            // Ambil ID tamu
            String queryTamu = "SELECT idTamu FROM tamu WHERE nama = ?";
            PreparedStatement pstTamu = conn.prepareStatement(queryTamu);
            pstTamu.setString(1, namaTamu);
            ResultSet rs = pstTamu.executeQuery();

            if (rs.next()) {
                int idTamu = rs.getInt("idTamu");

                String sql = "INSERT INTO pesanan (idTamu, idMakanan, jumlah) VALUES (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, idTamu);
                pst.setInt(2, idMakanan);
                pst.setInt(3, jumlah);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getPesananTamu(String namaTamu) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nama Makanan");
        model.addColumn("Jumlah");
        model.addColumn("Total");

        try {
            String sql = "SELECT m.nama, p.jumlah, (m.harga * p.jumlah) AS total " +
                         "FROM pesanan p JOIN tamu t ON p.idTamu = t.idTamu " +
                         "JOIN makanan m ON p.idMakanan = m.idMakanan " +
                         "WHERE t.nama = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaTamu);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nama"),
                    rs.getInt("jumlah"),
                    rs.getInt("total")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public int hitungTotalHarga(String namaTamu) {
        int total = 0;
        try {
            String sql = "SELECT SUM(m.harga * p.jumlah) AS total " +
                         "FROM pesanan p JOIN tamu t ON p.idTamu = t.idTamu " +
                         "JOIN makanan m ON p.idMakanan = m.idMakanan " +
                         "WHERE t.nama = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaTamu);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}