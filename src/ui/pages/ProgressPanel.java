package ui.pages;

import data.DataManager;
import model.User;
import ui.style.StyleUtils;

import javax.swing.*;
import java.awt.*;

public class ProgressPanel extends JPanel {

    public ProgressPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(StyleUtils.BG_COLOR);
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Verileri Çek
        User user = DataManager.getInstance().getCurrentUser();
        int points = user.getTotalPoints();
        int level = user.getCurrentLevel();
        int nextLevelPoints = level * 100; // Örnek: Level 2 için 200 puan lazım

        // 1. Başlık
        JLabel title = new JLabel("Progress & Points");
        title.setFont(StyleUtils.FONT_HEADER);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Track your achievements and level");
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 2. Mavi Seviye Kartı (Level Card)
        JPanel levelCard = new JPanel();
        levelCard.setLayout(null); // Tasarımı birebir yapmak için
        levelCard.setBackground(StyleUtils.PRIMARY_BLUE);
        levelCard.setMaximumSize(new Dimension(1000, 180));
        levelCard.setPreferredSize(new Dimension(800, 180));
        // Köşeleri yuvarlatmak Swing'de zordur, şimdilik düz panel yapıyoruz.

        JLabel lvlLabel = new JLabel("Level " + level);
        lvlLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lvlLabel.setForeground(Color.WHITE);
        lvlLabel.setBounds(30, 20, 200, 50);

        JLabel pointsLabel = new JLabel(points + " / " + nextLevelPoints + " points");
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setBounds(30, 80, 200, 20);

        JProgressBar progressBar = new JProgressBar(0, nextLevelPoints);
        progressBar.setValue(points);
        progressBar.setBounds(30, 110, 600, 15);
        progressBar.setBackground(new Color(255, 255, 255, 100)); // Saydam beyaz
        progressBar.setForeground(Color.ORANGE);
        progressBar.setBorderPainted(false);

        // Yıldızlar (Basit metin olarak)
        JLabel stars = new JLabel("★ ★ ★ ☆ ☆");
        stars.setFont(new Font("Segoe UI", Font.BOLD, 30));
        stars.setForeground(Color.ORANGE);
        stars.setBounds(650, 30, 200, 40);

        levelCard.add(lvlLabel);
        levelCard.add(pointsLabel);
        levelCard.add(progressBar);
        levelCard.add(stars);
        levelCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 3. Achievements (Başarılar) Başlığı
        JLabel badgeTitle = new JLabel("Achievements");
        badgeTitle.setFont(StyleUtils.FONT_BOLD);
        badgeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 4. Rozetler Paneli
        JPanel badgePanel = new JPanel(new GridLayout(1, 3, 20, 0));
        badgePanel.setBackground(StyleUtils.BG_COLOR);
        badgePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        badgePanel.setMaximumSize(new Dimension(1000, 120));

        badgePanel.add(createBadge("First Task", "Complete your first task", true));
        badgePanel.add(createBadge("10 Tasks Champion", "Complete 10 tasks", false));
        badgePanel.add(createBadge("Week Warrior", "5 tasks in a week", false));

        // Bileşenleri Ekle
        add(title);
        add(subtitle);
        add(Box.createVerticalStrut(20));
        add(levelCard);
        add(Box.createVerticalStrut(30));
        add(badgeTitle);
        add(Box.createVerticalStrut(10));
        add(badgePanel);
    }

    private JPanel createBadge(String title, String desc, boolean unlocked) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel icon = new JLabel(unlocked ? "🏆" : "🔒");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        icon.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel t = new JLabel(title);
        t.setFont(StyleUtils.FONT_BOLD);
        t.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel d = new JLabel("<html><center>" + desc + "</center></html>");
        d.setForeground(Color.GRAY);
        d.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        d.setHorizontalAlignment(SwingConstants.CENTER);

        if (unlocked) {
            t.setForeground(new Color(255, 140, 0)); // Turuncu
        } else {
            t.setForeground(Color.GRAY);
        }

        p.add(icon, BorderLayout.NORTH);
        p.add(t, BorderLayout.CENTER);
        p.add(d, BorderLayout.SOUTH);
        return p;
    }
}
