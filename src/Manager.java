import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;


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


}

