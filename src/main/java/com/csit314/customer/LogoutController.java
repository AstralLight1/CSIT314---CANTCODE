package com.csit314.customer;

// ============================================================
// USER STORY: CUSTOMER LOGOUT — Controller
// ============================================================
public class LogoutController {
    public boolean logout(String userID){return CustomerSession.end(userID);}
}
