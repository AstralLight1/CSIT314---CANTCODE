package com.csit314.platformmanager;
import javax.swing.*;
import java.awt.*;
public class LoginUI {
    public void displayLoginForm() {
        JFrame frame=new JFrame("Platform Manager Login");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel=new JPanel(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JTextField username=new JTextField(); JPasswordField password=new JPasswordField();
        JButton submit=new JButton("Login");
        panel.add(new JLabel("User ID:"));panel.add(username);
        panel.add(new JLabel("Password:"));panel.add(password);
        panel.add(new JLabel());panel.add(submit);
        submit.addActionListener(e -> {
            PlatformManager manager=new LoginController().authenticate(username.getText().trim(),new String(password.getPassword()));
            if(manager==null) displayErrorMsg();
            else { displaySuccessMsg(); frame.dispose(); new PlatformManagerMenu(manager).displayMenu(); }
        });
        frame.add(panel);frame.pack();frame.setLocationRelativeTo(null);frame.setVisible(true);
    }
    public void submitCredentials() { displayLoginForm(); }
    public void displaySuccessMsg() { JOptionPane.showMessageDialog(null,"Platform Manager login successful."); }
    public void displayErrorMsg() { JOptionPane.showMessageDialog(null,"Invalid credentials or inactive account."); }
}
