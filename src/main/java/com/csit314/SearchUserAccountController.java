package com.csit314;

// ============================================================
// USER STORY: SEARCH USER ACCOUNT — Controller
// ============================================================
public class SearchUserAccountController {
    public UserAccount searchAccount(String keyword) {
        return new UserAccount().searchAccount(keyword);
    }
}
