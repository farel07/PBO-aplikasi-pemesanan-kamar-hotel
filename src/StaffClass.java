import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StaffClass extends User {
    private Connection conn;

    public StaffClass() {
        try {
            conn = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getDataReservasi() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Reservasi");
        model.addColumn("Nama Tamu");
        model.addColumn("No. Kamar");
        model.addColumn("Check-In");
        model.addColumn("Check-Out");
        model.addColumn("Status");

        String sql = "SELECT tr.idReservasi, t.nama AS nama_tamu, k.no_kamar, tr.tgl_checkin, tr.tgl_checkout, tr.status " +
                     "FROM tamu_reservasi tr " +
                     "JOIN tamu t ON tr.idTamu = t.idTamu " +
                     "JOIN kamar k ON tr.idKamar = k.idKamar";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String statusStr = rs.getInt("status") == 0 ? "Aktif" : "Selesai";
                model.addRow(new Object[] {
                    rs.getInt("idReservasi"),
                    rs.getString("nama_tamu"),
                    rs.getString("no_kamar"),
                    rs.getDate("tgl_checkin"),
                    rs.getDate("tgl_checkout"),
                    statusStr
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public boolean hapusReservasi(int idReservasi) {
    String sql = "DELETE FROM tamu_reservasi WHERE idReservasi = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idReservasi);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean selesaikanReservasi(int idReservasi) {
    String updateReservasiSql = "UPDATE tamu_reservasi SET status = 1 WHERE idReservasi = ?";
    String updateKamarSql = "UPDATE kamar SET status = 'kosong' WHERE idKamar = (" +
                             "SELECT idKamar FROM tamu_reservasi WHERE idReservasi = ?)";

    try (PreparedStatement ps1 = conn.prepareStatement(updateReservasiSql);
         PreparedStatement ps2 = conn.prepareStatement(updateKamarSql)) {

        ps1.setInt(1, idReservasi);
        ps2.setInt(1, idReservasi);

        ps1.executeUpdate();
        ps2.executeUpdate();

        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public List<ItemCombo> getKamarKosong() {
        List<ItemCombo> list = new ArrayList<>();

        String sql = "SELECT idKamar, no_kamar FROM kamar WHERE status = 'kosong'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("idKamar");
                String noKamar = rs.getString("no_kamar");
                list.add(new ItemCombo(id, noKamar));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean tambahReservasi(int idTamu, int idKamar, java.sql.Date checkin, java.sql.Date checkout) {
    String sql = "INSERT INTO tamu_reservasi (idTamu, tgl_checkin, tgl_checkout, status, idKamar) VALUES (?, ?, ?, 0, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idTamu);
        stmt.setDate(2, checkin);
        stmt.setDate(3, checkout);
        stmt.setInt(4, idKamar);
        int rows = stmt.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan reservasi: " + e.getMessage());
        return false;
    }
}


}
