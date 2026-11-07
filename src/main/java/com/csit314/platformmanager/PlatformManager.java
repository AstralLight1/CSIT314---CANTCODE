package com.csit314.platformmanager;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
public class PlatformManager {
    private final String userID;
    private String sessionStatus = "Active";
    public PlatformManager(String userID) { this.userID = userID; }
    public String getUserID() { return userID; }
    public String getSessionStatus() { return sessionStatus; }
    public static PlatformManager login(String userID, String password) {
        String sql = "SELECT \"passwordHash\", \"accountStatus\" FROM public.user_accounts WHERE \"userID\"=? AND \"userType\"=4";
        try (Connection c=PlatformManagerDatabase.connect(); PreparedStatement p=c.prepareStatement(sql)) {
            p.setString(1,userID);
            try(ResultSet r=p.executeQuery()) {
                if(r.next() && "Active".equalsIgnoreCase(r.getString("accountStatus")) && BCrypt.checkpw(password,r.getString("passwordHash"))) return new PlatformManager(userID);
            }
        } catch(Exception e) { System.err.println("Platform Manager login: "+e.getMessage()); }
        return null;
    }
    public boolean logout() { sessionStatus="Logged Out"; return true; }
}
