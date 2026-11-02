package com.csit314;

// ============================================================
// USER ACCOUNT — Compatibility Controller
// Keeps existing UserAccountUI working alongside separate controllers.
// ============================================================
public class UserAccountController {
    // USER STORY: CREATE USER ACCOUNT
    public UserAccount createAccount(UserAccount details) {
        return new UserAccount().createAccount(details);
    }

    // USER STORY: VIEW USER ACCOUNT
    public UserAccount viewAccount(String userID) {
        return new UserAccount().getAccount(userID);
    }

    // USER STORY: UPDATE USER ACCOUNT
    public UserAccount updateAccount(UserAccount details) {
        return new UserAccount().updateAccount(details);
    }

    // USER STORY: SUSPEND USER ACCOUNT
    public boolean suspendAccount(String userID) {
        return new UserAccount().suspendAccount(userID);
    }

    // USER STORY: SEARCH USER ACCOUNT
    public UserAccount searchAccount(String keyword) {
        return new UserAccount().searchAccount(keyword);
    }
}
