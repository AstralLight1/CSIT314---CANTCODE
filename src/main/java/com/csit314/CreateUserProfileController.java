package com.csit314;

// ============================================================
// USER STORY: CREATE USER PROFILE — Controller
// ============================================================
public class CreateUserProfileController {
    public UserProfile createProfile(UserProfile details) {
        return new UserProfile().createProfile(details);
    }
}
