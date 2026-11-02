package com.csit314;
import javax.swing.*;
import java.awt.*;
public class UserProfileUI {
    private final UserProfileController controller = new UserProfileController();
    // ============================================================
    // USER STORY: PROFILE STORIES — Navigation
    // ============================================================
    public void displayMenu() {
        JFrame frame = new JFrame("User Admin System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(5,1,12,12));
        panel.setBorder(BorderFactory.createEmptyBorder(25,35,25,35));
        for (String name : new String[]{"Create User Profile", "View User Profile", "Update User Profile", "Suspend User Profile", "Search User Profile"}) {
            JButton button = new JButton(name);
            button.addActionListener(e -> {
                switch (name) {
                    case "Create User Profile" -> displayCreateForm();
                    case "View User Profile" -> viewProfile(JOptionPane.showInputDialog(frame,"User ID:"));
                    case "Update User Profile" -> displayUpdateForm(null);
                    case "Suspend User Profile" -> displaySuspendConfirmation();
                    case "Search User Profile" -> displaySearchForm();
                }
            });
            panel.add(button);
        }
        frame.add(panel); frame.setSize(380,320); frame.setLocationRelativeTo(null); frame.setVisible(true);
    }
    private UserProfile profileForm(String title) {
        JTextField id = new JTextField(), fullName = new JTextField(), email = new JTextField(), phone = new JTextField(), status = new JTextField("Active");
        JPanel p = new JPanel(new GridLayout(5,2,8,8));
        for (JComponent[] pair : new JComponent[][] {{new JLabel("User ID:"),id},{new JLabel("Full Name:"),fullName},{new JLabel("Email:"),email},{new JLabel("Phone Number:"),phone},{new JLabel("Status:"),status}}) {
            p.add(pair[0]); p.add(pair[1]);
        }
        if (JOptionPane.showConfirmDialog(null,p,title,JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return null;
        UserProfile profile = new UserProfile(); profile.setUserID(id.getText()); profile.setFullName(fullName.getText());
        profile.setEmail(email.getText()); profile.setPhoneNumber(phone.getText()); profile.setStatus(status.getText());
        return profile;
    }
    // ============================================================
    // USER STORY: CREATE USER PROFILE — Form
    // ============================================================
    public void displayCreateForm() { UserProfile details = profileForm("Create User Profile"); if(details != null) createProfile(details); }
    // ============================================================
    // USER STORY: CREATE USER PROFILE — Submit
    // ============================================================
    public void createProfile(UserProfile details) { if(controller.createProfile(details) != null) displaySuccess(); else displayError(); }
    // ============================================================
    // USER STORY: VIEW USER PROFILE — Retrieve
    // ============================================================
    public void viewProfile(String userID) {
        if (userID == null) return;
        UserProfile details = controller.viewProfile(userID);
        if(details != null) displayProfile(details); else displayError();
    }
    // ============================================================
    // USER STORY: VIEW / SEARCH USER PROFILE — Display
    // ============================================================
    public void displayProfile(UserProfile details) {
        JOptionPane.showMessageDialog(null, "User ID: " + details.getUserID() + "\nFull Name: " + details.getFullName() + "\nEmail: " + details.getEmail() + "\nPhone Number: " + details.getPhoneNumber() + "\nStatus: " + details.getStatus());
    }
    // ============================================================
    // USER STORY: UPDATE USER PROFILE — Form
    // ============================================================
    public void displayUpdateForm(UserProfile details) { UserProfile updated = profileForm("Update User Profile"); if(updated != null) updateProfile(updated); }
    // ============================================================
    // USER STORY: UPDATE USER PROFILE — Submit
    // ============================================================
    public void updateProfile(UserProfile details) { if(controller.updateProfile(details) != null) displaySuccess(); else displayError(); }
    // ============================================================
    // USER STORY: SUSPEND USER PROFILE — Confirmation
    // ============================================================
    public void displaySuspendConfirmation() {
        String userID = JOptionPane.showInputDialog(null,"User ID to suspend:");
        if(userID == null) return;
        if(JOptionPane.showConfirmDialog(null,"Suspend profile " + userID + "?","Confirm",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) suspendProfile(userID);
    }
    // ============================================================
    // USER STORY: SUSPEND USER PROFILE — Submit
    // ============================================================
    public void suspendProfile(String userID) { if(controller.suspendProfile(userID)) displaySuccess(); else displayError(); }
    // ============================================================
    // USER STORY: SEARCH USER PROFILE — Form
    // ============================================================
    public void displaySearchForm() {
        String userID = JOptionPane.showInputDialog(null, "Search User ID:");
        if (userID != null) searchProfile(userID);
    }
    // ============================================================
    // USER STORY: SEARCH USER PROFILE — Submit
    // ============================================================
    public void searchProfile(String userID) {
        UserProfile profile = controller.searchProfile(userID);
        if (profile != null) displayResults(profile); else displayError();
    }
    // ============================================================
    // USER STORY: SEARCH USER PROFILE — Display Results
    // ============================================================
    public void displayResults(UserProfile profile) { displayProfile(profile); }
    // ============================================================
    // USER STORY: PROFILE STORIES — Shared Success Message
    // ============================================================
    public void displaySuccess() { JOptionPane.showMessageDialog(null,"Success!"); }
    // ============================================================
    // USER STORY: PROFILE STORIES — Shared Error Message
    // ============================================================
    public void displayError() { JOptionPane.showMessageDialog(null,"Operation Failed!"); }
}
