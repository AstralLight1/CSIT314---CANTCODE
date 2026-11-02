package com.csit314;

// ============================================================
// USER STORY: UPDATE USER ACCOUNT — Controller
// ============================================================
public class UpdateUserAccountController {
    public UserAccount updateAccount(UserAccount details) {
        return new UserAccount().updateAccount(details);
    }
}
