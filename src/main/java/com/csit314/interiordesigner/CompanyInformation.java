
package com.csit314.interiordesigner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyInformation {

    private int companyID;
    private String designerID;
    private String companyName;
    private String companyDetails;

    public CompanyInformation(int id, String designer, String name, String details) {
        companyID = id;
        designerID = designer;
        companyName = name;
        companyDetails = details;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getDesignerID() {
        return designerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyDetails() {
        return companyDetails;
    }

    
    // ==========================================
    // #15: Create Company Information
    // ==========================================

    public static boolean saveCompanyInfo(String designer, String name, String details) {

        String sql = """
            INSERT INTO public.company_information
            ("designerUserID", "companyName", "companyDescription")
            VALUES (?, ?, ?)
            """;

        try (Connection c = IDDatabase.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, designer);
            ps.setString(2, name);
            ps.setString(3, details);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Create company: " + e.getMessage());
            return false;
        }
    }


    
    // ==========================================
    // #16: View Company Information
    // ==========================================

    public static CompanyInformation getCompanyInformation(String designerID) {

        String sql = """
            SELECT "companyID", "designerUserID",
                   "companyName", "companyDescription"
            FROM public.company_information
            WHERE "designerUserID" = ?
            """;

        try (Connection c = IDDatabase.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, designerID);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new CompanyInformation(
                        rs.getInt("companyID"),
                        rs.getString("designerUserID"),
                        rs.getString("companyName"),
                        rs.getString("companyDescription")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("View company: " + e.getMessage());
        }

        return null;
    }


    // #17: Update Company Information
    public static boolean updateCompanyInformation(
            int id, String designer, String name, String details) {

        String sql = """
            UPDATE public.company_information
            SET "companyName" = ?,
                "companyDescription" = ?
            WHERE "companyID" = ?
              AND "designerUserID" = ?
            """;

        try (Connection c = IDDatabase.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, details);
            ps.setInt(3, id);
            ps.setString(4, designer);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Update company: " + e.getMessage());
            return false;
        }
    }

    // #19: Remove Company Information
    public static boolean deleteCompanyInformation(int id, String designer) {

        String sql = """
            DELETE FROM public.company_information
            WHERE "companyID" = ?
              AND "designerUserID" = ?
            """;

        try (Connection c = IDDatabase.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, designer);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Remove company: " + e.getMessage());
            return false;
        }
    }
}
