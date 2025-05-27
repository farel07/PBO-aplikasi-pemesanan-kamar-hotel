package com.mycompany.checkinout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CheckInOutPanel extends JFrame {
    private JTextField txtIdReservasi;
    private JTextField txtNamaTamu;
    private JTextField txtNomorKamar;
    private JTextArea areaDetailReservasi;
    private JButton btnCariReservasi;
    private JButton btnCheckIn;
    private JButton btnCheckOut;

    public CheckInOutPanel() {
        setTitle("Form Check-In / Check-Out");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null); // Center window

        initComponents();
    }

    private void initComponents() {
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);

        // Panel Input
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(new TitledBorder("Data Reservasi/Tamu"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID Reservasi
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("ID Reservasi:"), gbc);
        txtIdReservasi = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        panelInput.add(txtIdReservasi, gbc);

        // Nama Tamu
        gbc.gridx = 0; gbc.gridy = 1;
        panelInput.add(new JLabel("Nama Tamu:"), gbc);
        txtNamaTamu = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        panelInput.add(txtNamaTamu, gbc);

        // Nomor Kamar
        gbc.gridx = 0; gbc.gridy = 2;
        panelInput.add(new JLabel("Nomor Kamar:"), gbc);
        txtNomorKamar = new JTextField(10);
        gbc.gridx = 1; gbc.gridy = 2;
        panelInput.add(txtNomorKamar, gbc);

        // Tombol Cari
        btnCariReservasi = new JButton("Cari Reservasi");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        panelInput.add(btnCariReservasi, gbc);

        // Panel Detail
        JPanel panelDetail = new JPanel(new BorderLayout());
        panelDetail.setBorder(new TitledBorder("Detail Reservasi/Kamar"));
        areaDetailReservasi = new JTextArea(8, 30);
        areaDetailReservasi.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaDetailReservasi);
        panelDetail.add(scrollPane, BorderLayout.CENTER);

        // Panel Tombol Aksi
        JPanel panelAksi = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCheckIn = new JButton("Check-in");
        btnCheckOut = new JButton("Check-out");
        panelAksi.add(btnCheckIn);
        panelAksi.add(btnCheckOut);

        // Tambahkan ke content panel
        contentPanel.add(panelInput, BorderLayout.NORTH);
        contentPanel.add(panelDetail, BorderLayout.CENTER);
        contentPanel.add(panelAksi, BorderLayout.SOUTH);

        // Action Listeners
        btnCariReservasi.addActionListener(e -> cariReservasi());
        btnCheckIn.addActionListener(e -> prosesCheckIn());
        btnCheckOut.addActionListener(e -> prosesCheckOut());
    }

    private void cariReservasi() {
        String idReservasi = txtIdReservasi.getText();
        String namaTamu = txtNamaTamu.getText();

        if (!idReservasi.isEmpty() || !namaTamu.isEmpty()) {
            areaDetailReservasi.setText("Mencari reservasi untuk ID: " + idReservasi + ", Nama: " + namaTamu + "...\n");
            areaDetailReservasi.append("Reservasi Ditemukan:\n");
            areaDetailReservasi.append("Nama Tamu: John Doe\n");
            areaDetailReservasi.append("Nomor Kamar: 101\n");
            areaDetailReservasi.append("Tipe Kamar: Deluxe\n");
            areaDetailReservasi.append("Status: Belum Check-in\n");
            txtNomorKamar.setText("101");
        } else {
            JOptionPane.showMessageDialog(this, "Masukkan ID Reservasi atau Nama Tamu.", "Input Kosong", JOptionPane.WARNING_MESSAGE);
            areaDetailReservasi.setText("");
        }
    }

    private void prosesCheckIn() {
        String nomorKamar = txtNomorKamar.getText();
        if (nomorKamar.isEmpty() || !areaDetailReservasi.getText().contains("Belum Check-in")) {
            JOptionPane.showMessageDialog(this, "Silakan cari reservasi yang valid terlebih dahulu atau pastikan tamu belum check-in.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Proses Check-in untuk kamar " + nomorKamar + " berhasil.", "Check-in Sukses", JOptionPane.INFORMATION_MESSAGE);
        areaDetailReservasi.append("\nSTATUS: CHECKED-IN");
    }

    private void prosesCheckOut() {
        String nomorKamar = txtNomorKamar.getText();
        if (nomorKamar.isEmpty() || !areaDetailReservasi.getText().contains("CHECKED-IN")) {
            JOptionPane.showMessageDialog(this, "Silakan cari reservasi yang sudah check-in untuk proses check-out.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Proses Check-out untuk kamar " + nomorKamar + " berhasil.", "Check-out Sukses", JOptionPane.INFORMATION_MESSAGE);
        areaDetailReservasi.setText("Kamar " + nomorKamar + " telah check-out.");
        txtIdReservasi.setText("");
        txtNamaTamu.setText("");
        txtNomorKamar.setText("");
    }

    // Jalankan sebagai aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CheckInOutPanel().setVisible(true);
        });
    }
}
