package com.csit314;

// ============================================================
// USER STORY: SUSPEND USER ACCOUNT — Controller
// ============================================================
public class SuspendUserAccountController {
    public boolean suspendAccount(String userID) {
        return new UserAccount().suspendAccount(userID);
    }
}
