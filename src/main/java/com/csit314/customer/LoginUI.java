package com.csit314.customer;

import javax.swing.*;
import java.awt.*;

// ============================================================
// USER STORY: CUSTOMER LOGIN — Boundary
// ============================================================
public class LoginUI {
    public void login(){
        JTextField id=new JTextField();JPasswordField password=new JPasswordField();
        JPanel panel=new JPanel(new GridLayout(2,2,8,8));
        panel.add(new JLabel("User ID:"));panel.add(id);
        panel.add(new JLabel("Password:"));panel.add(password);
        if(JOptionPane.showConfirmDialog(null,panel,"Customer Login",JOptionPane.OK_CANCEL_OPTION)!=JOptionPane.OK_OPTION)return;
        try{
            if(new LoginController().login(id.getText().trim(),new String(password.getPassword()))!=null){
                displaySuccess();new CustomerMenu().displayMenu();
            }else displayError();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,"Login failed: "+ex.getMessage());}
    }
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Customer login successful.");}
    public void displayError(){JOptionPane.showMessageDialog(null,"Invalid credentials or inactive customer account.");}
}
