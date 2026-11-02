package com.csit314;
public class UserProfileController {
    // ============================================================
    // USER STORY: CREATE USER PROFILE — Controller
    // ============================================================
    public UserProfile createProfile(UserProfile details) { return new UserProfile().createProfile(details); }
    // ============================================================
    // USER STORY: VIEW USER PROFILE — Controller
    // ============================================================
    public UserProfile viewProfile(String userID) { return new UserProfile().getProfile(userID); }
    // ============================================================
    // USER STORY: UPDATE USER PROFILE — Controller
    // ============================================================
    public UserProfile updateProfile(UserProfile details) { return new UserProfile().updateProfile(details); }
    // ============================================================
    // USER STORY: SEARCH USER PROFILE — Controller
    // ============================================================
    public UserProfile searchProfile(String userID) { return new UserProfile().getProfile(userID); }
    // ============================================================
    // USER STORY: SUSPEND USER PROFILE — Controller
    // ============================================================
    public boolean suspendProfile(String userID) { return new UserProfile().suspendProfile(userID); }
}
