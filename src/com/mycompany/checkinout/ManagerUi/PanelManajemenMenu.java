package com.mycompany.checkinout.ManagerUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class PanelManajemenMenu extends JPanel {
    private final JTable tabelMenu;
    private final DefaultTableModel modelTabelMenu;
    private final JButton btnTambahItem;
    private final JButton btnEditItem;
    private final JButton btnHapusItem;

    public PanelManajemenMenu() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabel Menu
        String[] kolom = {"ID Menu", "Nama Item", "Kategori", "Harga"}; // Sesuaikan kolom
        modelTabelMenu = new DefaultTableModel(kolom, 0) {
             @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelMenu = new JTable(modelTabelMenu);
        JScrollPane scrollPane = new JScrollPane(tabelMenu);

        // Tombol Aksi
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnTambahItem = new JButton("Tambah Item Menu");
        btnEditItem = new JButton("Edit Item Menu");
        btnHapusItem = new JButton("Hapus Item Menu");

        panelTombol.add(btnTambahItem);
        panelTombol.add(btnEditItem);
        panelTombol.add(btnHapusItem);

        add(scrollPane, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        muatDataMenu(); // Load data menu

        // TODO: Tambahkan ActionListeners untuk tombol-tombol
        btnTambahItem.addActionListener(e -> tambahItemMenu());
        btnEditItem.addActionListener(e -> editItemMenu());
        btnHapusItem.addActionListener(e -> hapusItemMenu());
    }

    private void muatDataMenu() {
        // TODO: Hubungkan dengan backend untuk mengambil data menu
        // Data dummy
        modelTabelMenu.addRow(new Object[]{"M001", "Nasi Goreng Spesial", "Makanan Utama", 35000});
        modelTabelMenu.addRow(new Object[]{"M002", "Ayam Bakar Madu", "Makanan Utama", 45000});
        modelTabelMenu.addRow(new Object[]{"D001", "Es Teh Manis", "Minuman", 10000});
    }
    
    private void tambahItemMenu() {
        // TODO: Tampilkan dialog untuk menambah item menu
        // DialogTambahEditMenuItem dialog = new DialogTambahEditMenuItem(...);
        // dialog.setVisible(true);
        // if (dialog.isBerhasil()) { ... }
        JOptionPane.showMessageDialog(this, "Fitur Tambah Item Menu belum diimplementasikan.");
    }

    private void editItemMenu() {
         int barisTerpilih = tabelMenu.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item menu yang ingin diedit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // TODO: Tampilkan dialog untuk mengedit item menu dengan data terpilih
        JOptionPane.showMessageDialog(this, "Fitur Edit Item Menu belum diimplementasikan.");
    }
    
    private void hapusItemMenu() {
        int barisTerpilih = tabelMenu.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item menu yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // TODO: Konfirmasi dan hapus item menu via backend
        JOptionPane.showMessageDialog(this, "Fitur Hapus Item Menu belum diimplementasikan.");
    }
}