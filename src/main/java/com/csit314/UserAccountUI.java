package com.csit314;
import javax.swing.*;
import java.awt.*;

public class UserAccountUI {
    private final UserAccountController controller = new UserAccountController();

    // ============================================================
    // USER STORY: ACCOUNT STORIES — Navigation
    // ============================================================
    public void displayMenu() {
        JFrame frame = new JFrame("User Admin System - Accounts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        String[] actions = {"Create User Account", "View User Account", "Update User Account",
                "Suspend User Account", "Search User Account"};
        for (String action : actions) {
            JButton button = new JButton(action);
            button.addActionListener(e -> {
                switch (action) {
                    case "Create User Account" -> displayCreateForm();
                    case "View User Account" -> viewAccount(JOptionPane.showInputDialog(frame, "User ID:"));
                    case "Update User Account" -> displayUpdateForm();
                    case "Suspend User Account" -> displaySuspendConfirmation();
                    case "Search User Account" -> displaySearchForm();
                }
            });
            panel.add(button);
        }
        frame.add(panel);
        frame.setSize(420, 370);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private UserAccount accountForm(String title, UserAccount existing) {
        JTextField id = new JTextField(existing == null ? "" : existing.getUserID());
        JPasswordField password = new JPasswordField();
        JComboBox<String> type = new JComboBox<>(new String[]{
                "User Admin", "Interior Designer", "Customer", "Platform Management"});
        JTextField status = new JTextField(existing == null ? "Active" : existing.getAccountStatus());
        if (existing != null) {
            id.setEditable(false);
            type.setSelectedIndex(Math.max(0, Math.min(3, existing.getUserType() - 1)));
        }
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("User ID:")); form.add(id);
        form.add(new JLabel(existing == null ? "Password:" : "New password (blank = keep):")); form.add(password);
        form.add(new JLabel("User Type:")); form.add(type);
        form.add(new JLabel("Account Status:")); form.add(status);
        if (JOptionPane.showConfirmDialog(null, form, title, JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return null;
        UserAccount details = new UserAccount();
        details.setUserID(id.getText());
        // Password is plaintext here only until UserAccount hashes it before saving.
        details.setPasswordHash(new String(password.getPassword()));
        details.setUserType(type.getSelectedIndex() + 1);
        details.setAccountStatus(status.getText());
        return details;
    }

    // ============================================================
    // USER STORY: CREATE USER ACCOUNT — Form
    // ============================================================
    public void displayCreateForm() {
        UserAccount details = accountForm("Create User Account", null);
        if (details != null) createAccount(details);
    }
    // ============================================================
    // USER STORY: CREATE USER ACCOUNT — Submit
    // ============================================================
    public void createAccount(UserAccount details) {
        if (controller.createAccount(details) != null) displaySuccess(); else displayError();
    }
    // ============================================================
    // USER STORY: VIEW USER ACCOUNT — Retrieve
    // ============================================================
    public void viewAccount(String userID) {
        if (userID == null) return;
        UserAccount account = controller.viewAccount(userID);
        if (account != null) displayAccount(account); else displayError();
    }
    // ============================================================
    // USER STORY: VIEW / SEARCH USER ACCOUNT — Display
    // ============================================================
    public void displayAccount(UserAccount account) {
        JOptionPane.showMessageDialog(null, "User ID: " + account.getUserID()
                + "\\nUser Type: " + account.getUserType()
                + "\\nAccount Status: " + account.getAccountStatus());
    }
    // ============================================================
    // USER STORY: UPDATE USER ACCOUNT — Form
    // ============================================================
    public void displayUpdateForm() {
        String userID = JOptionPane.showInputDialog(null, "User ID to update:");
        if (userID == null) return;
        UserAccount existing = controller.viewAccount(userID);
        if (existing == null) { displayError(); return; }
        UserAccount details = accountForm("Update User Account", existing);
        if (details != null) updateAccount(details);
    }
    // ============================================================
    // USER STORY: UPDATE USER ACCOUNT — Submit
    // ============================================================
    public void updateAccount(UserAccount details) {
        if (controller.updateAccount(details) != null) displaySuccess(); else displayError();
    }
    // ============================================================
    // USER STORY: SUSPEND USER ACCOUNT — Confirmation
    // ============================================================
    public void displaySuspendConfirmation() {
        String userID = JOptionPane.showInputDialog(null, "User ID to suspend:");
        if (userID == null) return;
        if (JOptionPane.showConfirmDialog(null, "Suspend account " + userID + "?",
                "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) suspendAccount(userID);
    }
    // ============================================================
    // USER STORY: SUSPEND USER ACCOUNT — Submit
    // ============================================================
    public void suspendAccount(String userID) {
        if (controller.suspendAccount(userID)) displaySuccess(); else displayError();
    }
    // ============================================================
    // USER STORY: SEARCH USER ACCOUNT — Form
    // ============================================================
    public void displaySearchForm() {
        String keyword = JOptionPane.showInputDialog(null, "Search User ID:");
        if (keyword != null) searchAccount(keyword);
    }
    // ============================================================
    // USER STORY: SEARCH USER ACCOUNT — Submit
    // ============================================================
    public void searchAccount(String keyword) {
        UserAccount result = controller.searchAccount(keyword);
        if (result != null) displayAccount(result); else displayError();
    }
    // ============================================================
    // USER STORY: ACCOUNT STORIES — Shared Success Message
    // ============================================================
    public void displaySuccess() { JOptionPane.showMessageDialog(null, "Success!"); }
    // ============================================================
    // USER STORY: ACCOUNT STORIES — Shared Error Message
    // ============================================================
    public void displayError() { JOptionPane.showMessageDialog(null, "Operation Failed!"); }
}
