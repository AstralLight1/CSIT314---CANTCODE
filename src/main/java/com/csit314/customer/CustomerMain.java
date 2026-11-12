package com.csit314.customer;

import javax.swing.SwingUtilities;

/** Optional standalone launcher. Integrate LoginUI into your existing Main instead if preferred. */
public class CustomerMain {
    public static void main(String[] args){com.csit314.ui.UiTheme.install();SwingUtilities.invokeLater(()->new LoginUI().login());}
}
