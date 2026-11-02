package com.csit314.interiordesigner;

import java.sql.*;
public class IDDatabase {
    public static Connection connect() throws SQLException {
        String url=System.getenv("SUPABASE_JDBC_URL"), user=System.getenv("SUPABASE_DB_USER"), pass=System.getenv("SUPABASE_DB_PASSWORD");
        if(url==null||user==null||pass==null) throw new SQLException("Missing Supabase environment variables");
        return DriverManager.getConnection(url,user,pass);
    }
}
