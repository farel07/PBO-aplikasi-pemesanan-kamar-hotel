package com.mycompany.checkinout.ManagerUi;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private PanelManajemenKamar panelManajemenKamar;
    private PanelManajemenMenu panelManajemenMenu; // Asumsi nama panel untuk menu

    public ManagerDashboardFrame(String managerUsername) {
        setTitle("Dashboard Manager - Selamat Datang, " + managerUsername);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Atau EXIT_ON_CLOSE jika ini frame utama
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        panelManajemenKamar = new PanelManajemenKamar();
        tabbedPane.addTab("Manajemen Kamar", panelManajemenKamar);

        // Anda perlu membuat PanelManajemenMenu.java serupa dengan PanelManajemenKamar
        panelManajemenMenu = new PanelManajemenMenu(); // Placeholder
        tabbedPane.addTab("Manajemen Menu Restoran", panelManajemenMenu);

        add(tabbedPane, BorderLayout.CENTER);
    }
    
    // Main method untuk testing frame ini secara terpisah
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Ganti "NamaManager" dengan username manager yang login
            new ManagerDashboardFrame("NamaManager").setVisible(true);
        });
    }
}