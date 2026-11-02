package com.csit314.customer;

import javax.swing.*;

// ============================================================
// USER STORY: CUSTOMER LOGOUT — Boundary
// ============================================================
public class LogoutUI {
    public boolean logout(){
        boolean ok=new LogoutController().logout(CustomerSession.getUserID());
        if(ok)displaySuccess();else displayError();
        return ok;
    }
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Logged out successfully.");}
    public void displayError(){JOptionPane.showMessageDialog(null,"Logout failed.");}
}
