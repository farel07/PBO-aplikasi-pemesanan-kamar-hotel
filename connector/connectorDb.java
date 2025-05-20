import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectorDb {
    
    // Ganti sesuai konfigurasi database kamu
    private static final String URL = "jdbc:mysql://localhost:3306/nama_database";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // defaultnya kosong

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();  // uji koneksi
    }
}
