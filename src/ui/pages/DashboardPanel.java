package ui.pages;

import data.DataManager;
import ui.style.StyleUtils;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(null); // Mutlak pozisyonlama (Tasarımı birebir oturtmak için basit yol)
        setBackground(StyleUtils.BG_COLOR);

        String username = DataManager.getInstance().getCurrentUser().getUsername();

        // 1. Hoşgeldin Başlığı
        JLabel welcome = new JLabel("Welcome back, " + username + "!");
        welcome.setFont(StyleUtils.FONT_HEADER);
        welcome.setBounds(40, 30, 400, 40);
        add(welcome);

        // 2. İstatistik Kartları (Points ve Level)
        add(createStatCard("Total Points", "120", Color.ORANGE, 40, 100));
        add(createStatCard("Current Level", "3", StyleUtils.PRIMARY_BLUE, 300, 100));

        // 3. Progress Snapshot
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(null);
        progressPanel.setBackground(Color.WHITE);
        progressPanel.setBounds(40, 250, 800, 150);

        JLabel pTitle = new JLabel("Progress Snapshot");
        pTitle.setFont(StyleUtils.FONT_BOLD);
        pTitle.setBounds(20, 20, 200, 20);

        JProgressBar progressBar = new JProgressBar(0, 400);
        progressBar.setValue(120);
        progressBar.setForeground(StyleUtils.PRIMARY_BLUE);
        progressBar.setBounds(20, 60, 760, 15);

        progressPanel.add(pTitle);
        progressPanel.add(progressBar);

        add(progressPanel);
    }

    private JPanel createStatCard(String title, String value, Color iconColor, int x, int y) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(x, y, 240, 120);

        JLabel titleLbl = new JLabel(title);
        titleLbl.setForeground(Color.GRAY);
        titleLbl.setBounds(20, 20, 100, 20);

        JLabel valueLbl = new JLabel(value);
        valueLbl.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLbl.setForeground(iconColor);
        valueLbl.setBounds(20, 50, 100, 40);

        card.add(titleLbl);
        card.add(valueLbl);
        return card;
    }
}
