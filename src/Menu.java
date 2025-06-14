/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Farrel
 */
public class Menu {
    private String nama;
    private int harga;

    private Connection conn;

    public Menu() {
        try {
            conn = Database.getConnection(); // Pastikan koneksi dilakukan di sini
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Menu(String nama, int harga){
        this.nama = nama;
        this.harga = harga;
    }

    public List<Object[]> getDataMenu() {
        List<Object[]> data = new ArrayList<>();
        String query = "SELECT id_menu, nama, harga FROM menu_restoran";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_menu"),
                    rs.getString("nama"),
                    rs.getInt("harga")
                };
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public boolean updateDataMenu(int id, String newNama, int harga) {
    String sql = "UPDATE menu_restoran SET nama = ?, harga = ? WHERE id_menu = ?";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, newNama);
        pst.setInt(2, harga); // Perbaikan di sini
        pst.setInt(3, id);
        return pst.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean insertDataMenu(String nama, int harga) {
    String sql = "INSERT INTO menu_restoran (nama, harga) VALUES (?, ?)";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, nama);
        pst.setInt(2, harga);
        return pst.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean hapusDataMenu(int id) {
    String sql = "DELETE FROM menu_restoran WHERE id_menu = ?";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, id);
        return pst.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean insertTransaksi(int idTamu, int total, ArrayList<Object[]> pesanan) {
        try {
            conn.setAutoCommit(false);

            String sqlTrans = "INSERT INTO transaksi(tgl_transaksi, total, idTamu) VALUES (CURRENT_DATE(), ?, ?)";
            PreparedStatement pstTrans = conn.prepareStatement(sqlTrans, Statement.RETURN_GENERATED_KEYS);
            pstTrans.setInt(1, total);
            pstTrans.setInt(2, idTamu);
            pstTrans.executeUpdate();

            ResultSet rs = pstTrans.getGeneratedKeys();
            if (!rs.next()) throw new SQLException("Gagal ambil ID transaksi.");
            int idTransaksi = rs.getInt(1);

            String sqlDetail = "INSERT INTO detail_transaksi(id_transaksi, id_menu, qty) VALUES (?, ?, ?)";
            PreparedStatement pstDetail = conn.prepareStatement(sqlDetail);

            for (Object[] item : pesanan) {
                int idMenu = (int) item[0];
                int qty = (int) item[3];
                pstDetail.setInt(1, idTransaksi);
                pstDetail.setInt(2, idMenu);
                pstDetail.setInt(3, qty);
                pstDetail.addBatch();
            }

            pstDetail.executeBatch();
            conn.commit();
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignored) {}
            e.printStackTrace();
            return false;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }




}
