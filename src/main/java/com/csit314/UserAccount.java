package com.csit314;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserAccount {
    private String userID;
    private String passwordHash;
    private int userType;
    private String accountStatus;

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public int getUserType() { return userType; }
    public void setUserType(int userType) { this.userType = userType; }
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }

    // ============================================================
    // USER STORY: LOGIN — Entity
    // ============================================================
    public UserAccount login(String userID, String password, int userType) {
        String url = System.getenv("SUPABASE_JDBC_URL");
        String dbUser = System.getenv("SUPABASE_DB_USER");
        String dbPassword = System.getenv("SUPABASE_DB_PASSWORD");
        if (url == null || dbUser == null || dbPassword == null) {
            System.err.println("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
            return null;
        }
        String sql = "SELECT \"userID\", \"passwordHash\", \"userType\", \"accountStatus\" " +
                     "FROM public.user_accounts WHERE \"userID\" = ? AND \"userType\" = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userID);
            statement.setInt(2, userType);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) return null;
                String hash = rs.getString("passwordHash");
                if (hash == null || !BCrypt.checkpw(password, hash)) return null;
                UserAccount account = new UserAccount();
                account.setUserID(rs.getString("userID"));
                account.setPasswordHash(hash);
                account.setUserType(rs.getInt("userType"));
                account.setAccountStatus(rs.getString("accountStatus"));
                return account;
            }
        } catch (SQLException | IllegalArgumentException ex) {
            System.err.println("Login error: " + ex.getMessage());
            return null;
        }
    }

    private Connection connect() throws SQLException {
        String url = System.getenv("SUPABASE_JDBC_URL");
        String user = System.getenv("SUPABASE_DB_USER");
        String password = System.getenv("SUPABASE_DB_PASSWORD");
        if (url == null || user == null || password == null)
            throw new SQLException("Set SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD.");
        return DriverManager.getConnection(url, user, password);
    }

    private UserAccount fromResult(ResultSet rs) throws SQLException {
        UserAccount account = new UserAccount();
        account.setUserID(rs.getString("userID"));
        account.setPasswordHash(rs.getString("passwordHash"));
        account.setUserType(rs.getInt("userType"));
        account.setAccountStatus(rs.getString("accountStatus"));
        return account;
    }

    // ============================================================
    // USER STORY: CREATE USER ACCOUNT — Entity
    // ============================================================
    public UserAccount createAccount(UserAccount details) {
        String sql = "INSERT INTO public.user_accounts (\"userID\", \"passwordHash\", \"userType\", \"accountStatus\") VALUES (?, ?, ?, ?)";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, details.getUserID());
            ps.setString(2, BCrypt.hashpw(details.getPasswordHash(), BCrypt.gensalt()));
            ps.setInt(3, details.getUserType());
            ps.setString(4, details.getAccountStatus());
            return ps.executeUpdate() == 1 ? details : null;
        } catch (SQLException | IllegalArgumentException ex) {
            System.err.println("Create account error: " + ex.getMessage());
            return null;
        }
    }

    // ============================================================
    // USER STORY: VIEW USER ACCOUNT — Entity
    // ============================================================
    public UserAccount getAccount(String userID) {
        String sql = "SELECT \"userID\", \"passwordHash\", \"userType\", \"accountStatus\" FROM public.user_accounts WHERE \"userID\" = ?";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? fromResult(rs) : null;
            }
        } catch (SQLException ex) {
            System.err.println("View account error: " + ex.getMessage());
            return null;
        }
    }

    // ============================================================
    // USER STORY: UPDATE USER ACCOUNT — Entity
    // ============================================================
    public UserAccount updateAccount(UserAccount details) {
        boolean changePassword = details.getPasswordHash() != null && !details.getPasswordHash().isEmpty();
        String sql = changePassword
                ? "UPDATE public.user_accounts SET \"passwordHash\"=?, \"userType\"=?, \"accountStatus\"=? WHERE \"userID\"=?"
                : "UPDATE public.user_accounts SET \"userType\"=?, \"accountStatus\"=? WHERE \"userID\"=?";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            int i = 1;
            if (changePassword) ps.setString(i++, BCrypt.hashpw(details.getPasswordHash(), BCrypt.gensalt()));
            ps.setInt(i++, details.getUserType());
            ps.setString(i++, details.getAccountStatus());
            ps.setString(i, details.getUserID());
            return ps.executeUpdate() > 0 ? details : null;
        } catch (SQLException | IllegalArgumentException ex) {
            System.err.println("Update account error: " + ex.getMessage());
            return null;
        }
    }

    // ============================================================
    // USER STORY: SUSPEND USER ACCOUNT — Entity
    // ============================================================
    public boolean suspendAccount(String userID) {
        String sql = "UPDATE public.user_accounts SET \"accountStatus\"='Suspended' WHERE \"userID\"=?";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Suspend account error: " + ex.getMessage());
            return false;
        }
    }

    // ============================================================
    // USER STORY: SEARCH USER ACCOUNT — Entity
    // ============================================================
    public UserAccount searchAccount(String keyword) {
        String sql = "SELECT \"userID\", \"passwordHash\", \"userType\", \"accountStatus\" FROM public.user_accounts WHERE \"userID\" ILIKE ? ORDER BY \"userID\" LIMIT 1";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? fromResult(rs) : null;
            }
        } catch (SQLException ex) {
            System.err.println("Search account error: " + ex.getMessage());
            return null;
        }
    }

}
