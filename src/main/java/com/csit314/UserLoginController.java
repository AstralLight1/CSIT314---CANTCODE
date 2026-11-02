package com.csit314;

public class UserLoginController {
    // ============================================================
    // USER STORY: LOGIN — Controller
    // ============================================================
    public UserAccount login(String userID, String password, int userType) {
        return new UserAccount().login(userID, password, userType);
    }
}
