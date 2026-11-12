package com.csit314;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.csit314.customer.CustomerMenu;
import com.csit314.customer.CustomerSession;
import com.csit314.interiordesigner.InteriorDesignerMenu;

/** USER STORY: LOGIN — Boundary. Presentation refreshed; authentication and routing unchanged. */
public class UserLoginUI {
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeBox;
    private static final Color NAVY = new Color(25, 44, 75);
    private static final Color BLUE = new Color(43, 102, 190);
    private static final Color MUTED = new Color(104, 118, 139);
    private static final Color BG = new Color(244, 247, 252);

    public void displayLoginForm() {
        JFrame frame = new JFrame("CSIT314 | Interior Design Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650, 600));

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(30, 24, 30, 24));
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(new LineBorder(new Color(224, 232, 243), 1, true), new EmptyBorder(34, 44, 36, 44)));
        card.setPreferredSize(new Dimension(500, 505));

        JLabel emblem = new JLabel("ID", SwingConstants.CENTER);
        emblem.setOpaque(true);
        emblem.setBackground(new Color(228, 237, 251));
        emblem.setForeground(BLUE);
        emblem.setFont(new Font("SansSerif", Font.BOLD, 20));
        emblem.setPreferredSize(new Dimension(54, 54));
        emblem.setMaximumSize(new Dimension(54, 54));
        emblem.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(emblem);
        card.add(Box.createVerticalStrut(18));

        JLabel title = new JLabel("Welcome back");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(NAVY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(7));
        JLabel subtitle = new JLabel("Interior Design Management System");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(28));

        userIDField = new JTextField();
        passwordField = new JPasswordField();
        userTypeBox = new JComboBox<>(new String[]{"User Admin", "Interior Designer", "Customer", "Platform Management"});
        // Keep every form control in the same centered, fixed-width column.
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setOpaque(false);
        form.setPreferredSize(new Dimension(410, 265));
        form.setMaximumSize(new Dimension(410, 265));
        form.setAlignmentX(Component.CENTER_ALIGNMENT);
        addField(form, "User ID", userIDField);
        addField(form, "Password", passwordField);
        addField(form, "User Type", userTypeBox);
        card.add(form);
        card.add(Box.createVerticalStrut(15));

        JButton signIn = new JButton("Sign in");
        signIn.setFont(new Font("SansSerif", Font.BOLD, 15));
        signIn.setForeground(Color.WHITE);
        signIn.setBackground(BLUE);
        signIn.setOpaque(true);
        signIn.setContentAreaFilled(true);
        signIn.setFocusPainted(false);
        signIn.setBorder(new EmptyBorder(12, 16, 12, 16));
        signIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signIn.setMaximumSize(new Dimension(410, 46));
        signIn.setPreferredSize(new Dimension(410, 46));
        card.add(signIn);
        card.add(Box.createVerticalStrut(20));
        JLabel footer = new JLabel("CSIT314  •  Interior Design Platform");
        footer.setForeground(MUTED);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(footer);

        signIn.addActionListener(e -> {
            if (login()) {
                String userID = userIDField.getText().trim();
                int userType = userTypeBox.getSelectedIndex() + 1;
                frame.dispose();
                if (userType == 1) new UserAdminMenu().displayMenu();
                else if (userType == 2) new InteriorDesignerMenu(userID).displayMenu();
                else if (userType == 3) {
                    CustomerSession.start(userID);
                    new CustomerMenu().displayMenu();
                } else if (userType == 4) {
                    new com.csit314.platformmanager.PlatformManagerMenu(
                        new com.csit314.platformmanager.PlatformManager(userID)).displayMenu();
                }
            }
        });
        root.add(card);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(720, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addField(JPanel form, String name, JComponent field) {
        JLabel label = new JLabel(name);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(NAVY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setPreferredSize(new Dimension(410, 18));
        label.setMaximumSize(new Dimension(410, 18));
        form.add(label);
        form.add(Box.createVerticalStrut(7));

        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setForeground(NAVY);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setPreferredSize(new Dimension(410, 39));
        field.setMinimumSize(new Dimension(410, 39));
        field.setMaximumSize(new Dimension(410, 39));
        if (!(field instanceof JComboBox)) {
            field.setBorder(new CompoundBorder(
                new LineBorder(new Color(210, 221, 235)),
                new EmptyBorder(6, 10, 6, 10)));
        }
        // Preserve the native combo-box border so the selected role isn't clipped.
        form.add(field);
        form.add(Box.createVerticalStrut(15));
    }

    // USER STORY: LOGIN — Submit Login (existing controller and logic)
    public boolean login() {
        String userID = userIDField.getText().trim();
        String password = new String(passwordField.getPassword());
        int userType = userTypeBox.getSelectedIndex() + 1;
        UserAccount account = new UserLoginController().login(userID, password, userType);
        if (account != null) { displaySuccess(); return true; }
        displayError();
        return false;
    }
    public void displaySuccess() { JOptionPane.showMessageDialog(null, "Login Successful!"); }
    public void displayError() { JOptionPane.showMessageDialog(null, "Login Failed!"); }
}
