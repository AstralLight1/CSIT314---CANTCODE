package com.csit314;

// ============================================================
// USER STORY: SEARCH USER PROFILE — Controller
// ============================================================
public class SearchUserProfileController {
    public UserProfile searchProfile(String userID) {
        return new UserProfile().getProfile(userID);
    }
}
