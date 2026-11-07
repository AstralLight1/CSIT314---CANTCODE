package com.csit314.platformmanager;
import java.sql.*;
public class PlatformManagerDatabase {
    public static Connection connect() throws SQLException {
        return com.csit314.interiordesigner.IDDatabase.connect();
    }
}
