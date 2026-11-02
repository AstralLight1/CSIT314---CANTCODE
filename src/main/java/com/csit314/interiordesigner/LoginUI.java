package com.csit314.interiordesigner;

import javax.swing.*;
public class LoginUI {
    // #13: Designer login (shared application login remains the entry point)
    public void login(){new com.csit314.UserLoginUI().displayLoginForm();}
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Login Successful!");}
    public void displayError(){JOptionPane.showMessageDialog(null,"Login Failed!");}
}
