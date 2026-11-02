package com.csit314;

// ============================================================
// USER STORY: VIEW USER ACCOUNT — Controller
// ============================================================
public class ViewUserAccountController {
    public UserAccount viewAccount(String userID) {
        return new UserAccount().getAccount(userID);
    }
}
