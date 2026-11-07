package com.csit314;
import javax.swing.*;
import java.awt.*;
import com.csit314.ui.UiTheme;
public class UserAdminMenu {
    public void displayMenu() {
        JFrame frame = new JFrame("CSIT314 | User Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel page = UiTheme.page("User Admin", "Manage accounts and profiles");
        JPanel grid = UiTheme.grid(3, 1);
        JButton accounts = UiTheme.action("Manage User Accounts");
        JButton profiles = UiTheme.action("Manage User Profiles");
        JButton logout = UiTheme.action("Logout");
        accounts.addActionListener(e -> new UserAccountUI().displayMenu());
        profiles.addActionListener(e -> new UserProfileUI().displayMenu());
        logout.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                frame.dispose(); new UserLoginUI().displayLoginForm();
            }
        });
        grid.add(accounts); grid.add(profiles); grid.add(logout);
        page.add(grid, BorderLayout.CENTER);
        frame.add(page); frame.setSize(520, 400); frame.setLocationRelativeTo(null); frame.setVisible(true);
    }
}
