package com.csit314.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/** Presentation-only Swing theme. No controllers, entities, SQL, or listeners are changed. */
public final class UiTheme {
    private UiTheme() {}
    public static final Color NAVY = new Color(23, 42, 71);
    public static final Color BLUE = new Color(36, 100, 191);
    public static final Color BACKGROUND = new Color(245, 248, 252);
    private static final Color MUTED = new Color(99, 115, 135);
    private static final Color BORDER = new Color(213, 224, 237);
    private static final Color WHITE = Color.WHITE;

    public static void install() {
        Font regular = new Font("SansSerif", Font.PLAIN, 14);
        Font bold = new Font("SansSerif", Font.BOLD, 14);
        String[] regularKeys = {"Label.font", "TextField.font", "PasswordField.font",
            "ComboBox.font", "TextArea.font", "TextPane.font", "List.font",
            "Table.font", "TableHeader.font", "TabbedPane.font", "CheckBox.font",
            "RadioButton.font", "OptionPane.messageFont", "OptionPane.buttonFont"};
        for (String key : regularKeys) UIManager.put(key, regular);
        UIManager.put("Button.font", bold);
        UIManager.put("Panel.background", BACKGROUND);
        UIManager.put("Viewport.background", WHITE);
        UIManager.put("ScrollPane.background", WHITE);
        UIManager.put("Label.foreground", NAVY);
        UIManager.put("TextField.background", WHITE);
        UIManager.put("PasswordField.background", WHITE);
        UIManager.put("TextArea.background", WHITE);
        UIManager.put("TextField.foreground", NAVY);
        UIManager.put("PasswordField.foreground", NAVY);
        UIManager.put("TextField.caretForeground", BLUE);
        UIManager.put("TextField.selectionBackground", new Color(207, 226, 251));
        UIManager.put("TextField.selectionForeground", NAVY);
        UIManager.put("Table.background", WHITE);
        UIManager.put("Table.foreground", NAVY);
        UIManager.put("Table.selectionBackground", new Color(221, 235, 252));
        UIManager.put("Table.selectionForeground", NAVY);
        UIManager.put("Table.gridColor", new Color(232, 238, 246));
        UIManager.put("TableHeader.background", new Color(232, 240, 250));
        UIManager.put("TableHeader.foreground", NAVY);
        UIManager.put("Button.background", WHITE);
        UIManager.put("Button.foreground", NAVY);
        UIManager.put("Button.focus", BACKGROUND);
        UIManager.put("ComboBox.background", WHITE);
        UIManager.put("ComboBox.foreground", NAVY);
        UIManager.put("OptionPane.background", BACKGROUND);
        UIManager.put("OptionPane.messageForeground", NAVY);
        UIManager.put("TabbedPane.selected", WHITE);
    }

    public static JPanel page(String title, String subtitle) {
        JPanel root = new JPanel(new BorderLayout(0, 22));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(30, 36, 32, 36));
        JPanel header = new JPanel(new BorderLayout(0, 9));
        header.setOpaque(false);
        JLabel heading = new JLabel(title);
        heading.setFont(new Font("SansSerif", Font.BOLD, 27));
        heading.setForeground(NAVY);
        JLabel detail = new JLabel(subtitle);
        detail.setFont(new Font("SansSerif", Font.PLAIN, 13));
        detail.setForeground(MUTED);
        header.add(heading, BorderLayout.NORTH);
        header.add(detail, BorderLayout.CENTER);
        JPanel accent = new JPanel();
        accent.setPreferredSize(new Dimension(0, 3));
        accent.setBackground(BLUE);
        header.add(accent, BorderLayout.SOUTH);
        root.add(header, BorderLayout.NORTH);
        return root;
    }

    public static JButton action(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBackground(WHITE);
        button.setForeground(NAVY);
        button.setBorder(new CompoundBorder(new LineBorder(BORDER, 1, true),
            new EmptyBorder(13, 18, 13, 18)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    public static JPanel grid(int rows, int columns) {
        JPanel grid = new JPanel(new GridLayout(rows, columns, 14, 14));
        grid.setOpaque(false);
        return grid;
    }
}
