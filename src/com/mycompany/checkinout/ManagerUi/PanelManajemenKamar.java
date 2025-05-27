package com.mycompany.checkinout.ManagerUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelManajemenKamar extends JPanel {

    private final JTable tabelKamar;
    private final DefaultTableModel modelTabelKamar;
    private final JButton btnTambahKamar;
    private final JButton btnEditKamar;
    private final JButton btnHapusKamar;

    public PanelManajemenKamar() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabel Kamar
        String[] kolom = {"Nomor Kamar", "Tipe", "Status", "Harga/Malam"};
        modelTabelKamar = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat sel tidak bisa diedit langsung
            }
        };
        tabelKamar = new JTable(modelTabelKamar);
        JScrollPane scrollPane = new JScrollPane(tabelKamar);

        // Tombol Aksi
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnTambahKamar = new JButton("Tambah Kamar");
        btnEditKamar = new JButton("Edit Kamar");
        btnHapusKamar = new JButton("Hapus Kamar");

        panelTombol.add(btnTambahKamar);
        panelTombol.add(btnEditKamar);
        panelTombol.add(btnHapusKamar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        // Load data kamar (dummy untuk sekarang)
        muatDataKamar();

        // Action Listeners
        btnTambahKamar.addActionListener(e -> tambahKamar());
        btnEditKamar.addActionListener(e -> editKamar());
        btnHapusKamar.addActionListener(e -> hapusKamar());
    }

    private void muatDataKamar() {
        // TODO: Hubungkan dengan logika backend untuk mengambil daftar kamar
        // Contoh: List<Kamar> daftarKamar = kamarService.getAllKamar();
        // modelTabelKamar.setRowCount(0); // Bersihkan tabel dulu
        // for (Kamar kamar : daftarKamar) {
        //    modelTabelKamar.addRow(new Object[]{kamar.getNomor(), kamar.getTipe(), kamar.getStatus(), kamar.getHarga()});
        // }

        // Data dummy
        modelTabelKamar.addRow(new Object[]{"101", "Standard", "Tersedia", 300000});
        modelTabelKamar.addRow(new Object[]{"102", "Standard", "Terisi", 300000});
        modelTabelKamar.addRow(new Object[]{"201", "Deluxe", "Tersedia", 500000});
        modelTabelKamar.addRow(new Object[]{"301", "Suite", "Perbaikan", 800000});
    }

    private void tambahKamar() {
        // TODO: Tampilkan dialog untuk menambah kamar baru
        DialogTambahEditKamar dialog = new DialogTambahEditKamar((Frame) SwingUtilities.getWindowAncestor(this), "Tambah Kamar Baru", null);
        dialog.setVisible(true);
        if (dialog.isBerhasil()) {
            // TODO: Dapatkan data dari dialog dan kirim ke backend
            // Object[] dataKamarBaru = dialog.getDataKamar();
            // kamarService.addKamar(dataKamarBaru);
            // muatDataKamar(); // Refresh tabel
            JOptionPane.showMessageDialog(this, "Kamar baru akan ditambahkan (implementasi backend).");
            // Dummy add to table
            modelTabelKamar.addRow(dialog.getDataKamar());
        }
    }

    private void editKamar() {
        int barisTerpilih = tabelKamar.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kamar yang ingin diedit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // TODO: Ambil data kamar terpilih dari model atau backend
        // Kamar kamarTerpilih = kamarService.getKamarByNomor(modelTabelKamar.getValueAt(barisTerpilih, 0).toString());
        
        // Data dummy untuk dikirim ke dialog
        Object[] dataKamarUntukEdit = new Object[]{
            modelTabelKamar.getValueAt(barisTerpilih, 0),
            modelTabelKamar.getValueAt(barisTerpilih, 1),
            modelTabelKamar.getValueAt(barisTerpilih, 2),
            modelTabelKamar.getValueAt(barisTerpilih, 3)
        };

        DialogTambahEditKamar dialog = new DialogTambahEditKamar((Frame) SwingUtilities.getWindowAncestor(this), "Edit Kamar", dataKamarUntukEdit);
        dialog.setVisible(true);

        if (dialog.isBerhasil()) {
            // TODO: Dapatkan data dari dialog dan kirim ke backend untuk update
            // Object[] dataKamarUpdate = dialog.getDataKamar();
            // kamarService.updateKamar(dataKamarUpdate);
            // muatDataKamar(); // Refresh tabel
            JOptionPane.showMessageDialog(this, "Kamar akan diupdate (implementasi backend).");
             // Dummy update to table
            modelTabelKamar.removeRow(barisTerpilih);
            modelTabelKamar.insertRow(barisTerpilih, dialog.getDataKamar());
        }
    }

    private void hapusKamar() {
        int barisTerpilih = tabelKamar.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kamar yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus kamar " + modelTabelKamar.getValueAt(barisTerpilih, 0) + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            // TODO: Hubungkan dengan logika backend untuk menghapus kamar
            // String nomorKamar = modelTabelKamar.getValueAt(barisTerpilih, 0).toString();
            // kamarService.deleteKamar(nomorKamar);
            // muatDataKamar(); // Refresh tabel
            JOptionPane.showMessageDialog(this, "Kamar akan dihapus (implementasi backend).");
            modelTabelKamar.removeRow(barisTerpilih);
        }
    }
}