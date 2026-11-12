package com.csit314.platformmanager;
public class PlatformManagerLauncher {
    public static void main(String[] args){com.csit314.ui.UiTheme.install();javax.swing.SwingUtilities.invokeLater(()->new LoginUI().displayLoginForm());}
}
