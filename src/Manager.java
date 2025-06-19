import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Manager extends User {
    private Connection conn;

    public Manager() throws SQLException {
        this.conn = Database.getConnection(); // Inisialisasi koneksi di constructor
    }

    public List<Object[]> getDataKamar() {
        List<Object[]> data = new ArrayList<>();
        String query = "SELECT idKamar, no_kamar, status FROM kamar";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getInt("idKamar"),
                    rs.getString("no_kamar"),
                    rs.getString("status")
                };
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public boolean updateNoKamar(int idKamar, String newNoKamar) {
        String sql = "UPDATE kamar SET no_kamar = ? WHERE idKamar = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newNoKamar);
            pst.setInt(2, idKamar);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Object[]> cariKamar(String keyword) throws SQLException {
    List<Object[]> data = new ArrayList<>();
    String sql = "SELECT idKamar, no_kamar, status FROM kamar WHERE no_kamar LIKE ?";

    if (conn == null || conn.isClosed()) {
        conn = Database.getConnection();
    }

    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, "%" + keyword + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Object[] row = {
                rs.getInt("idKamar"),
                rs.getString("no_kamar"),
                rs.getString("status")
            };
            data.add(row);
        }
    }

    return data;
}
    
    public boolean tambahKamar(String noKamar) throws SQLException {
    if (conn == null || conn.isClosed()) {
        conn = Database.getConnection();
    }

    String sql = "INSERT INTO kamar (no_kamar) VALUES (?)";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, noKamar.trim());
        return pst.executeUpdate() > 0;
    }
}

    public boolean editNoKamar(int idKamar, String noKamarBaru) throws SQLException {
    if (conn == null || conn.isClosed()) {
        conn = Database.getConnection();
    }

    String sql = "UPDATE kamar SET no_kamar = ? WHERE idKamar = ?";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, noKamarBaru.trim());
        pst.setInt(2, idKamar);
        return pst.executeUpdate() > 0;
    }
}

    public boolean hapusKamar(int idKamar) throws SQLException {
    if (conn == null || conn.isClosed()) {
        conn = Database.getConnection();
    }

    String sql = "DELETE FROM kamar WHERE idKamar = ?";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, idKamar);
        return pst.executeUpdate() > 0;
    }
}

public List<Object[]> getSemuaTransaksi() {
    List<Object[]> list = new ArrayList<>();
    try {
        String sql = "SELECT t.id_transaksi, t.tgl_transaksi, t.total, tm.nama " +
                     "FROM transaksi t " +
                     "JOIN tamu tm ON t.idTamu = tm.idTamu " +
                     "ORDER BY t.id_transaksi DESC";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id_transaksi");
            Date tgl = rs.getDate("tgl_transaksi");
            int total = rs.getInt("total");
            String namaTamu = rs.getString("nama");

            list.add(new Object[]{id, tgl, total, namaTamu});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public List<Object[]> getDetailTransaksi(int idTransaksi) {
    List<Object[]> data = new ArrayList<>();
    String sql = """
        SELECT 
            d.id_transaksi, d.id_menu, m.nama, m.harga, d.qty, (m.harga * d.qty) AS subtotal
        FROM 
            detail_transaksi d
        JOIN 
            menu_restoran m ON d.id_menu = m.id_menu
        WHERE 
            d.id_transaksi = ?
    """;
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, idTransaksi);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            data.add(new Object[]{
                rs.getInt("id_transaksi"),
                rs.getInt("id_menu"),
                rs.getString("nama"),
                rs.getInt("harga"),
                rs.getInt("qty"),
                rs.getInt("subtotal")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return data;
}




}

