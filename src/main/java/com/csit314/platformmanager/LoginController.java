package com.csit314.platformmanager;
public class LoginController {
    public PlatformManager authenticate(String username,String password) { return PlatformManager.login(username,password); }
}
