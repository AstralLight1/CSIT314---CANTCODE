package com.csit314.customer;

import com.csit314.UserAccount;

// ============================================================
// USER STORY: CUSTOMER LOGIN — Controller
// ============================================================
public class LoginController {
    public UserAccount login(String userID,String password){
        if(userID==null||userID.isBlank()||password==null)return null;
        // Reuse existing User Admin entity and its password verification.
        UserAccount account=new UserAccount().login(userID,password,3);
        if(account!=null)CustomerSession.start(userID);
        return account;
    }
}
