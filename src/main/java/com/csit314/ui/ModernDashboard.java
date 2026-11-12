package com.csit314.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/** Boundary-only dashboard layout. Existing button listeners remain untouched. */
public final class ModernDashboard {
    private static final Color NAVY = new Color(15, 35, 69);
    private static final Color BLUE = new Color(39, 105, 215);
    private static final Color BG = new Color(244, 248, 253);
    private ModernDashboard() {}

    public static void show(JFrame frame, String role, String subtitle, JButton[] actions) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(NAVY);
        sidebar.setPreferredSize(new Dimension(245, 0));
        sidebar.setBorder(new EmptyBorder(30, 20, 25, 20));
        JPanel brand = new JPanel(); brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("CSIT314"); logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 25)); logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel product = new JLabel("INTERIOR DESIGN PLATFORM");
        product.setForeground(new Color(164, 191, 225)); product.setFont(new Font("SansSerif", Font.BOLD, 10));
        product.setAlignmentX(Component.LEFT_ALIGNMENT);
        brand.add(logo); brand.add(Box.createVerticalStrut(7)); brand.add(product);
        brand.add(Box.createVerticalStrut(32));
        JLabel nav = new JLabel("WORKSPACE"); nav.setForeground(new Color(153, 177, 211));
        nav.setFont(new Font("SansSerif", Font.BOLD, 11)); nav.setAlignmentX(Component.LEFT_ALIGNMENT);
        brand.add(nav); sidebar.add(brand, BorderLayout.NORTH);
        JPanel navigation = new JPanel(); navigation.setOpaque(false);
        navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
        for (int i = 0; i < actions.length; i++) {
            JButton original = actions[i];
            JButton link = new JButton((i == actions.length - 1 && original.getText().equalsIgnoreCase("Logout") ? "↪  " : "▸  ") + original.getText());
            link.setHorizontalAlignment(SwingConstants.LEFT);
            link.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            link.setPreferredSize(new Dimension(200, 44));
            link.setFont(new Font("SansSerif", Font.PLAIN, 12));
            link.setForeground(new Color(231, 239, 253));
            link.setBackground(i == 0 ? new Color(34, 76, 135) : NAVY);
            link.setOpaque(true); link.setContentAreaFilled(true);
            link.setBorder(new EmptyBorder(9, 12, 9, 6));
            link.setFocusPainted(false); link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            link.addActionListener(e -> original.doClick());
            navigation.add(link); navigation.add(Box.createVerticalStrut(6));
        }
        JScrollPane sideScroll = new JScrollPane(navigation);
        sideScroll.setBorder(null); sideScroll.setOpaque(false); sideScroll.getViewport().setOpaque(false);
        sideScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sidebar.add(sideScroll, BorderLayout.CENTER);
        JLabel foot = new JLabel("Desktop workspace  •  Java Swing");
        foot.setForeground(new Color(154, 178, 207)); foot.setFont(new Font("SansSerif", Font.PLAIN, 10));
        sidebar.add(foot, BorderLayout.SOUTH);
        frame.add(sidebar, BorderLayout.WEST);

        JPanel main = new JPanel(new BorderLayout(0, 22)); main.setBackground(BG);
        main.setBorder(new EmptyBorder(30, 36, 32, 36));
        JPanel header = new JPanel(new BorderLayout()); header.setOpaque(false);
        JPanel headings = new JPanel(); headings.setOpaque(false);
        headings.setLayout(new BoxLayout(headings, BoxLayout.Y_AXIS));
        JLabel eyebrow = new JLabel("DASHBOARD  /  " + role.toUpperCase());
        eyebrow.setForeground(BLUE); eyebrow.setFont(new Font("SansSerif", Font.BOLD, 11));
        JLabel title = new JLabel("Welcome to " + role);
        title.setForeground(NAVY); title.setFont(new Font("SansSerif", Font.BOLD, 27));
        JLabel desc = new JLabel(subtitle); desc.setForeground(new Color(105, 122, 145));
        desc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        headings.add(eyebrow); headings.add(Box.createVerticalStrut(12)); headings.add(title);
        headings.add(Box.createVerticalStrut(8)); headings.add(desc);
        header.add(headings, BorderLayout.WEST);
        JLabel status = new JLabel("●  Workspace ready");
        status.setForeground(new Color(35, 135, 93)); status.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.add(status, BorderLayout.EAST); main.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(0, 18)); content.setOpaque(false);
        JLabel section = new JLabel("Quick actions"); section.setForeground(NAVY);
        section.setFont(new Font("SansSerif", Font.BOLD, 19));
        content.add(section, BorderLayout.NORTH);
        JPanel cards = new JPanel(new GridLayout(0, 2, 16, 16)); cards.setOpaque(false);
        for (int i = 0; i < actions.length; i++) {
            JButton original = actions[i];
            JPanel card = new JPanel(new BorderLayout(0, 10)); card.setBackground(Color.WHITE);
            card.setBorder(new CompoundBorder(new LineBorder(new Color(220, 229, 240), 1, true),
                new EmptyBorder(20, 20, 18, 20)));
            JLabel symbol = new JLabel(original.getText().equalsIgnoreCase("Logout") ? "↪" : "▣");
            symbol.setFont(new Font("SansSerif", Font.BOLD, 23)); symbol.setForeground(BLUE);
            card.add(symbol, BorderLayout.NORTH);
            JPanel bottom = new JPanel(new BorderLayout(0, 9)); bottom.setOpaque(false);
            JLabel name = new JLabel("<html>" + original.getText() + "</html>");
            name.setFont(new Font("SansSerif", Font.BOLD, 15)); name.setForeground(NAVY);
            bottom.add(name, BorderLayout.NORTH);
            JButton launch = new JButton("Open  →"); launch.setHorizontalAlignment(SwingConstants.LEFT);
            launch.setFont(new Font("SansSerif", Font.BOLD, 12)); launch.setForeground(BLUE);
            launch.setBackground(Color.WHITE); launch.setBorderPainted(false);
            launch.setFocusPainted(false); launch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            launch.addActionListener(e -> original.doClick()); bottom.add(launch, BorderLayout.SOUTH);
            card.add(bottom, BorderLayout.CENTER); cards.add(card);
        }
        JScrollPane scroll = new JScrollPane(cards);
        scroll.setBorder(null); scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scroll, BorderLayout.CENTER);
        main.add(content, BorderLayout.CENTER);
        JLabel footer = new JLabel("CSIT314  •  Interior Design Management System");
        footer.setForeground(new Color(130, 145, 163)); footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        main.add(footer, BorderLayout.SOUTH); frame.add(main, BorderLayout.CENTER);
        frame.setSize(1080, 720); frame.setMinimumSize(new Dimension(850, 580));
        frame.setLocationRelativeTo(null); frame.setVisible(true);
    }
}
