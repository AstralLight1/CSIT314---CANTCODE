
package com.csit314;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.csit314.customer.CustomerMenu;
import com.csit314.customer.CustomerSession;
import com.csit314.interiordesigner.InteriorDesignerMenu;

public class UserLoginUI {

    private JTextField userIDField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeBox;

    // ============================================================
    // USER STORY: LOGIN — Display Login Form
    // ============================================================
    public void displayLoginForm() {

        JFrame frame = new JFrame("CSIT314 | Interior Design Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel page = com.csit314.ui.UiTheme.page("Welcome back", "Sign in to the Interior Design Management System");
        JPanel panel = new JPanel(new GridLayout(4, 2, 12, 16));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        panel.add(new JLabel("User ID:"));
        userIDField = new JTextField();
        panel.add(userIDField);
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        panel.add(new JLabel("User Type:"));
        userTypeBox = new JComboBox<>(new String[]{
            "User Admin", "Interior Designer", "Customer", "Platform Management"
        });
        panel.add(userTypeBox);
        JButton button = com.csit314.ui.UiTheme.action("Sign in");
        panel.add(new JLabel());
        panel.add(button);
        button.addActionListener(e -> {

            if (login()) {

                String userID = userIDField.getText().trim();
                int userType = userTypeBox.getSelectedIndex() + 1;

                frame.dispose();

                // USER STORY: USER ADMIN LOGIN
                if (userType == 1) {
                    new UserAdminMenu().displayMenu();
                }

                // USER STORY: INTERIOR DESIGNER LOGIN
                else if (userType == 2) {
                    new InteriorDesignerMenu(userID).displayMenu();
                }

                // USER STORY: CUSTOMER LOGIN
                else if (userType == 3) {
                    CustomerSession.start(userID);
                    new CustomerMenu().displayMenu();
                }

                // USER STORY: PLATFORM MANAGEMENT LOGIN
                else if (userType == 4) {
                new com.csit314.platformmanager.PlatformManagerMenu(
                new com.csit314.platformmanager.PlatformManager(userID)
                 ).displayMenu();
}
            }
        });

        page.add(panel, java.awt.BorderLayout.CENTER);
        frame.add(page);
        frame.setSize(580, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ============================================================
    // USER STORY: LOGIN — Submit Login
    // ============================================================
    public boolean login() {

        String userID = userIDField.getText().trim();
        String password = new String(passwordField.getPassword());
        int userType = userTypeBox.getSelectedIndex() + 1;

        UserAccount account = new UserLoginController().login(
            userID,
            password,
            userType
        );

        if (account != null) {
            displaySuccess();
            return true;
        }

        displayError();
        return false;
    }

    // ============================================================
    // USER STORY: LOGIN — Success Message
    // ============================================================
    public void displaySuccess() {
        JOptionPane.showMessageDialog(
            null,
            "Login Successful!"
        );
    }

    // ============================================================
    // USER STORY: LOGIN — Error Message
    // ============================================================
    public void displayError() {
        JOptionPane.showMessageDialog(
            null,
            "Login Failed!"
        );
    }
}
