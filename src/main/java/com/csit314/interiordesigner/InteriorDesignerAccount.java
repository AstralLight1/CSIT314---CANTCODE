package com.csit314.interiordesigner;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
public class InteriorDesignerAccount {
    private String userID;
    private String sessionStatus;
    public InteriorDesignerAccount(String userID){this.userID=userID;this.sessionStatus="Active";}
    public String getUserID(){return userID;}
    public String getSessionStatus(){return sessionStatus;}
    // #13: LOGIN — reuse shared user_accounts table, role 2
    public static InteriorDesignerAccount login(String userID,String password){
        String sql="SELECT \"passwordHash\", \"accountStatus\" FROM public.user_accounts WHERE \"userID\"=? AND \"userType\"=2";
        try(Connection c=IDDatabase.connect(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,userID);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next() && "Active".equalsIgnoreCase(rs.getString("accountStatus")) && BCrypt.checkpw(password,rs.getString("passwordHash"))) return new InteriorDesignerAccount(userID);
            }
        }catch(Exception e){System.err.println("Designer login: "+e.getMessage());}
        return null;
    }
    // #14: LOGOUT — clear local session; no persistent sessions table
    public boolean logout(){sessionStatus="Logged Out";return true;}
}
