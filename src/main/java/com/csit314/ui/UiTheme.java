package com.csit314.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/** Presentation-only Swing theme. No controllers, entities, SQL, or listeners are changed. */
public final class UiTheme {
    private UiTheme() {}
    public static final Color NAVY = new Color(23, 42, 71);
    public static final Color BLUE = new Color(36, 100, 191);
    public static final Color BACKGROUND = new Color(245, 248, 252);
    private static final Color MUTED = new Color(99, 115, 135);
    private static final Color BORDER = new Color(213, 224, 237);
    private static final Color WHITE = Color.WHITE;

    private static boolean listenerInstalled = false;

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
        UIManager.put("OptionPane.background", BACKGROUND);
        UIManager.put("OptionPane.messageForeground", NAVY);
        UIManager.put("Button.select", new Color(216, 231, 250));
        UIManager.put("TabbedPane.contentAreaColor", WHITE);
        UIManager.put("TabbedPane.selected", WHITE);
        UIManager.put("ToolTip.background", WHITE);
        UIManager.put("ToolTip.foreground", NAVY);
        if (!listenerInstalled) {
            listenerInstalled = true;
            Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
                if (event instanceof WindowEvent windowEvent
                        && windowEvent.getID() == WindowEvent.WINDOW_OPENED) {
                    Window window = windowEvent.getWindow();
                    SwingUtilities.invokeLater(() -> styleWindow(window));
                }
            }, AWTEvent.WINDOW_EVENT_MASK);
        }

    }

    /** Apply presentation styling to every role's existing Swing screens and dialogs. */
    private static void styleWindow(Window window) {
        if (!(window instanceof JFrame || window instanceof JDialog)) return;
        if (window instanceof JFrame frame) {
            frame.getContentPane().setBackground(BACKGROUND);
            if (frame.getWidth() < 420) frame.setMinimumSize(new Dimension(420, frame.getHeight()));
        }
        styleTree(window);
        // Swing's JOptionPane dialogs are packed before WINDOW_OPENED fires.
        // Our larger input/button borders change preferred sizes afterwards;
        // repack dialogs so the OK/Cancel buttons never fall below the window.
        if (window instanceof JDialog dialog) {
            Point previousLocation = dialog.getLocation();
            dialog.pack();
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            dialog.setSize(Math.min(dialog.getWidth(), screen.width - 40),
                           Math.min(dialog.getHeight(), screen.height - 80));
            dialog.setLocation(Math.max(0, Math.min(previousLocation.x, screen.width - dialog.getWidth())),
                               Math.max(0, Math.min(previousLocation.y, screen.height - dialog.getHeight())));
        } else {
            window.revalidate();
        }
        window.repaint();
    }

    private static void styleTree(Component component) {
        if (component instanceof JButton button) {
            button.setFont(new Font("SansSerif", Font.BOLD, 13));
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            if (button.getBackground() instanceof javax.swing.plaf.ColorUIResource) {
                button.setBackground(WHITE);
                button.setForeground(NAVY);
            }
            if (button.getBorder() instanceof javax.swing.plaf.UIResource) {
                button.setBorder(new CompoundBorder(new LineBorder(BORDER, 1, true),
                    new EmptyBorder(9, 14, 9, 14)));
            }
        } else if (component instanceof JTextField field) {
            styleInput(field);
        } else if (component instanceof JTextArea area) {
            area.setFont(new Font("SansSerif", Font.PLAIN, 14));
            area.setBackground(WHITE);
            area.setForeground(NAVY);
            if (area.getBorder() instanceof javax.swing.plaf.UIResource)
                area.setBorder(new CompoundBorder(new LineBorder(BORDER, 1, true),
                    new EmptyBorder(7, 9, 7, 9)));
        } else if (component instanceof JTable table) {
            table.setRowHeight(Math.max(table.getRowHeight(), 31));
            table.setShowVerticalLines(false);
            table.setGridColor(BORDER);
            table.setSelectionBackground(new Color(221, 235, 252));
            table.getTableHeader().setPreferredSize(new Dimension(0, 34));
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        } else if (component instanceof JScrollPane scroll) {
            scroll.getViewport().setBackground(WHITE);
            if (scroll.getBorder() instanceof javax.swing.plaf.UIResource)
                scroll.setBorder(new LineBorder(BORDER, 1, true));
        } else if (component instanceof JComboBox<?> combo) {
            combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
            combo.setBackground(WHITE);
        }
        if (component instanceof Container container)
            for (Component child : container.getComponents()) styleTree(child);
    }

    private static void styleInput(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBackground(WHITE);
        field.setForeground(NAVY);
        field.setCaretColor(BLUE);
        if (field.getBorder() instanceof javax.swing.plaf.UIResource)
            field.setBorder(new CompoundBorder(new LineBorder(BORDER, 1, true),
                new EmptyBorder(7, 9, 7, 9)));
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
