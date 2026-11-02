package com.csit314.interiordesigner;

import javax.swing.*;
public class LogoutUI {
    // #14: Logout UI -> Controller -> Entity
    public void logout(javax.swing.JFrame frame,InteriorDesignerAccount account){
        if(JOptionPane.showConfirmDialog(frame,"Logout?","Confirm",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        if(new LogoutController().logout(account)){
            frame.dispose(); displaySuccess(); new com.csit314.UserLoginUI().displayLoginForm();
        }else JOptionPane.showMessageDialog(frame,"Logout failed");
    }
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Logged out successfully");}
}
