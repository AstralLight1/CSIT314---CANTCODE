package com.csit314;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfile {
    private String userID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String status;

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // ============================================================
    // USER STORY: CREATE USER PROFILE — Entity
    // ============================================================
    public UserProfile createProfile(UserProfile details) {
        String url = System.getenv("SUPABASE_JDBC_URL");
        String user = System.getenv("SUPABASE_DB_USER");
        String password = System.getenv("SUPABASE_DB_PASSWORD");
        if (url == null || user == null || password == null) {
            System.err.println("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
            return null;
        }
        String sql = "INSERT INTO user_profiles (\"userID\", \"fullName\", email, \"phoneNumber\") VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, details.getUserID());
            statement.setString(2, details.getFullName());
            statement.setString(3, details.getEmail());
            statement.setString(4, details.getPhoneNumber());
            return statement.executeUpdate() == 1 ? details : null;
        } catch (SQLException ex) {
            System.err.println("Create profile failed: " + ex.getMessage());
            return null;
        }
    }

    // ============================================================
    // USER STORY: VIEW / SEARCH USER PROFILE — Entity
    // ============================================================
    public UserProfile getProfile(String userID) {
        String url = System.getenv("SUPABASE_JDBC_URL");
        String user = System.getenv("SUPABASE_DB_USER");
        String password = System.getenv("SUPABASE_DB_PASSWORD");
        if (url == null || user == null || password == null) {
            System.err.println("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
            return null;
        }
        String sql = "SELECT \"userID\", \"fullName\", email, \"phoneNumber\", status FROM public.user_profiles WHERE \"userID\" = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userID);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    UserProfile profile = new UserProfile();
                    profile.setUserID(result.getString("userID"));
                    profile.setFullName(result.getString("fullName"));
                    profile.setEmail(result.getString("email"));
                    profile.setPhoneNumber(result.getString("phoneNumber"));
                    profile.setStatus(result.getString("status"));
                    return profile;
                }
            }
        } catch (Exception e) {
            System.err.println("Unable to retrieve profile: " + e.getMessage());
        }
        return null;
    }

    // ============================================================
    // USER STORY: UPDATE USER PROFILE — Entity
    // ============================================================
    public UserProfile updateProfile(UserProfile details) {
        String url = System.getenv("SUPABASE_JDBC_URL");
        String dbUser = System.getenv("SUPABASE_DB_USER");
        String dbPassword = System.getenv("SUPABASE_DB_PASSWORD");
        if (url == null || dbUser == null || dbPassword == null) {
            System.err.println("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
            return null;
        }
        String sql = "UPDATE user_profiles SET \"fullName\"=?, email=?, \"phoneNumber\"=?, status=? WHERE \"userID\"=?";
        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, details.getFullName());
            statement.setString(2, details.getEmail());
            statement.setString(3, details.getPhoneNumber());
            statement.setString(4, details.getStatus());
            statement.setString(5, details.getUserID());
            return statement.executeUpdate() > 0 ? details : null;
        } catch (SQLException e) {
            System.err.println("Unable to update profile: " + e.getMessage());
            return null;
        }
    }

    // ============================================================
    // USER STORY: SUSPEND USER PROFILE — Entity
    // ============================================================
    public boolean suspendProfile(String userID) {
        String url = System.getenv("SUPABASE_JDBC_URL");
        String user = System.getenv("SUPABASE_DB_USER");
        String password = System.getenv("SUPABASE_DB_PASSWORD");
        if (url == null || user == null || password == null) {
            System.err.println("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
            return false;
        }
        String sql = "UPDATE user_profiles SET status = 'Suspended' WHERE \"userID\" = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userID);
            return statement.executeUpdate() > 0;
        } catch (Exception ex) {
            System.err.println("Unable to suspend profile: " + ex.getMessage());
            return false;
        }
    }
}
