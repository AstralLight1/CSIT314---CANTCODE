package com.csit314;

// ============================================================
// USER STORY: CREATE USER ACCOUNT — Controller
// ============================================================
public class CreateUserAccountController {
    public UserAccount createAccount(UserAccount details) {
        return new UserAccount().createAccount(details);
    }
}
