package com.mycompany.checkinout.ManagerUi;

import javax.swing.*;
import java.awt.*;

public class DialogTambahEditKamar extends JDialog {
    private final JTextField txtNomorKamar;
    private final JComboBox<String> comboTipeKamar;
    private final JComboBox<String> comboStatusKamar;
    private final JTextField txtHarga;
    private final JButton btnSimpan;
    private final JButton btnBatal;
    private boolean berhasil = false;
    private Object[] dataKamar; // Untuk menyimpan hasil input

    public DialogTambahEditKamar(Frame owner, String title, Object[] dataAwal) {
        super(owner, title, true); // true untuk modal
        setSize(400, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10,10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Nomor Kamar:"), gbc);
        txtNomorKamar = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        panelForm.add(txtNomorKamar, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Tipe Kamar:"), gbc);
        comboTipeKamar = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite", "Family"});
        gbc.gridx = 1; gbc.gridy = 1;
        panelForm.add(comboTipeKamar, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Status Kamar:"), gbc);
        comboStatusKamar = new JComboBox<>(new String[]{"Tersedia", "Terisi", "Perbaikan", "Dibersihkan"});
        gbc.gridx = 1; gbc.gridy = 2;
        panelForm.add(comboStatusKamar, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelForm.add(new JLabel("Harga/Malam:"), gbc);
        txtHarga = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 3;
        panelForm.add(txtHarga, gbc);

        if (dataAwal != null) { // Mode Edit
            txtNomorKamar.setText(dataAwal[0].toString());
            txtNomorKamar.setEditable(false); // Nomor kamar biasanya tidak diedit
            comboTipeKamar.setSelectedItem(dataAwal[1].toString());
            comboStatusKamar.setSelectedItem(dataAwal[2].toString());
            txtHarga.setText(dataAwal[3].toString());
        }

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");
        panelTombol.add(btnSimpan);
        panelTombol.add(btnBatal);

        add(panelForm, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        btnSimpan.addActionListener(e -> simpan());
        btnBatal.addActionListener(e -> dispose());
    }

    private void simpan() {
        // TODO: Validasi input
        if (txtNomorKamar.getText().trim().isEmpty() || txtHarga.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nomor Kamar dan Harga tidak boleh kosong.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Integer.parseInt(txtHarga.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        dataKamar = new Object[]{
                txtNomorKamar.getText(),
                comboTipeKamar.getSelectedItem().toString(),
                comboStatusKamar.getSelectedItem().toString(),
                Integer.valueOf(txtHarga.getText()) // Simpan harga sebagai integer
        };
        berhasil = true;
        dispose();
    }

    public boolean isBerhasil() {
        return berhasil;
    }

    public Object[] getDataKamar() {
        return dataKamar;
    }
}