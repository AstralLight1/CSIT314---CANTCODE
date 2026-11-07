
package com.csit314.platformmanager;

import javax.swing.JFrame;

public class LogoutUI {

    // USER STORY 40: PLATFORM MANAGER LOGOUT

    public void displayLogoutOption() {
    }

    public void submitLogout(JFrame frame, PlatformManager manager) {
        if (new LogoutController().logout(manager)) {
            frame.dispose();
            displayLoginPage();
        }
    }

    public void displayLoginPage() {
        new com.csit314.UserLoginUI().displayLoginForm();
    }
}
