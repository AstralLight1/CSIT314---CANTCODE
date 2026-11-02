
package com.csit314;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserAdminMenu {

    // ============================================================
    // USER STORY: MAIN MENU — Account and Profile Stories
    // ============================================================
    public void displayMenu() {

        JFrame frame = new JFrame("CSIT314 User Admin System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(35, 40, 35, 40));

        JButton accounts = new JButton("Manage User Accounts");
        JButton profiles = new JButton("Manage User Profiles");
        JButton logout = new JButton("Logout");

        // Manage User Accounts
        accounts.addActionListener(e ->
            new UserAccountUI().displayMenu()
        );

        // Manage User Profiles
        profiles.addActionListener(e ->
            new UserProfileUI().displayMenu()
        );

        // ========================================================
        // USER STORY #2: LOGOUT
        // ========================================================
        logout.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                new UserLoginUI().displayLoginForm();
            }
        });

        panel.add(accounts);
        panel.add(profiles);
        panel.add(logout);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

