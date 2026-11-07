package com.csit314;
import javax.swing.SwingUtilities;
import com.csit314.ui.UiTheme;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UiTheme.install();
            new UserLoginUI().displayLoginForm();
        });
    }
}
