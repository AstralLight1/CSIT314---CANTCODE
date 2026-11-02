package com.csit314.customer;

import java.sql.*;

/** Shared Supabase connection; credentials come from environment variables. */
public final class CustomerDatabase {
    private CustomerDatabase() {}
    public static Connection connect() throws SQLException {
        String url=System.getenv("SUPABASE_JDBC_URL");
        String user=System.getenv("SUPABASE_DB_USER");
        String pass=System.getenv("SUPABASE_DB_PASSWORD");
        if(url==null||url.isBlank()||user==null||user.isBlank()||pass==null||pass.isBlank())
            throw new SQLException("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
        return DriverManager.getConnection(url,user,pass);
    }
}
