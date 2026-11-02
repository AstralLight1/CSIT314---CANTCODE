package com.csit314;

// ============================================================
// USER STORY: SUSPEND USER PROFILE — Controller
// ============================================================
public class SuspendUserProfileController {
    public boolean suspendProfile(String userID) {
        return new UserProfile().suspendProfile(userID);
    }
}
