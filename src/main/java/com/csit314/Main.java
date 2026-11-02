package com.csit314;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new UserLoginUI().displayLoginForm()); }
}
