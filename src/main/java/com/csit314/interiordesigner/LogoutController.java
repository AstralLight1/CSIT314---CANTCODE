package com.csit314.interiordesigner;

public class LogoutController {
    // #14: Interior Designer Logout
    public boolean logout(InteriorDesignerAccount account){return account!=null && account.logout();}
}
