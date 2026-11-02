package com.csit314;

// ============================================================
// USER STORY: UPDATE USER PROFILE — Controller
// ============================================================
public class UpdateUserProfileController {
    public UserProfile updateProfile(UserProfile details) {
        return new UserProfile().updateProfile(details);
    }
}
