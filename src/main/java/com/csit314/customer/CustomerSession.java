package com.csit314.customer;

/** Stores the logged-in customer for this application session. */
public final class CustomerSession {
    private static String userID;
    private CustomerSession() {}
    public static String getUserID(){return userID;}
    public static void start(String id){userID=id;}
    public static boolean end(String id){
        if(userID==null||!userID.equals(id))return false;
        userID=null; return true;
    }
}
