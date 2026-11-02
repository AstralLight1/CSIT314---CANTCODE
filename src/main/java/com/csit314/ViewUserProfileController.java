package com.csit314;

// ============================================================
// USER STORY: VIEW USER PROFILE — Controller
// ============================================================
public class ViewUserProfileController {
    public UserProfile viewProfile(String userID) {
        return new UserProfile().getProfile(userID);
    }
}
