/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Farrel
 */
public class TamuClass {
    private String namaTamu;

    private Connection conn;

    public TamuClass() {
        try {
            conn = Database.getConnection(); // Pastikan koneksi dilakukan di sini
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Constructor
    public TamuClass(String namaTamu) {
        this.namaTamu = namaTamu;
    }


    public List<Object[]> getDataTamu() {
        List<Object[]> data = new ArrayList<>();
        String query = "SELECT idTamu, nama FROM tamu";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("idTamu"),
                    rs.getString("nama")
                };
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public boolean updateNamaTamu(int idTamu, String newNama) {
        String sql = "UPDATE tamu SET nama = ? WHERE idTamu = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newNama);
            pst.setInt(2, idTamu);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTamu(int idTamu) {
    String sql = "DELETE FROM tamu WHERE idTamu = ?";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, idTamu);
        return pst.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean tambahTamu(String nama) {
    String sql = "INSERT INTO tamu (nama) VALUES (?)";
    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, nama);
        return pst.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<ItemCombo> getAllTamu() {
        List<ItemCombo> list = new ArrayList<>();

        String sql = "SELECT idTamu, nama FROM tamu";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("idTamu");
                String nama = rs.getString("nama");
                list.add(new ItemCombo(id, nama));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // Getter
    public String getNamaTamu() {
        return namaTamu;
    }

    // Setter
    public void setNamaTamu(String namaTamu) {
        this.namaTamu = namaTamu;
    }

    // Optional: tampilkan info tamu
    @Override
    public String toString() {
        return "Tamu: " + namaTamu;
    }
}

