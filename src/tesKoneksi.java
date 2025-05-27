/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Farrel
 */
public class tesKoneksi {
     public static void main(String[] args) {
        try {
            Connection conn = Database.getConnection();
            if (conn != null) {
                System.out.println("Koneksi ke database BERHASIL!");
                conn.close(); // tutup koneksi setelah tes
            } else {
                System.out.println("Koneksi ke database GAGAL!");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
