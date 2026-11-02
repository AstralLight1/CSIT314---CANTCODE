package com.csit314.interiordesigner;

import java.sql.*;
import java.util.*;

public class CurrentInteriorDesignProject {
    private final int projectID;
    private final String projectName, projectDetails, designerID;

    public CurrentInteriorDesignProject(int id, String name, String details, String designer) {
        this.projectID = id;
        this.projectName = name;
        this.projectDetails = details;
        this.designerID = designer;
    }

    public int getProjectID() { return projectID; }
    public String getProjectName() { return projectName; }
    public String getProjectDetails() { return projectDetails; }
    public String getDesignerID() { return designerID; }

    // ============================================================
    // USER STORY #20: CREATE CURRENT INTERIOR DESIGN PROJECT — Entity
    // ============================================================
    public static boolean createProject(String designer, String name, String details) {
        String sql = "INSERT INTO public.interior_design_projects " +
                     "(designer_id, project_name, project_details, project_status) " +
                     "VALUES (?, ?, ?, 'Current')";
        try (Connection c = IDDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, designer);
            ps.setString(2, name);
            ps.setString(3, details);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Create project: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // USER STORY #21: VIEW CURRENT INTERIOR DESIGN PROJECTS — Entity
    // ============================================================
    public static List<CurrentInteriorDesignProject> getCurrentProjects(String designer) {
        return searchProjects(designer, "");
    }

    // ============================================================
    // USER STORY #22: UPDATE CURRENT INTERIOR DESIGN PROJECT — Entity
    // ============================================================
    public static boolean updateProject(String designer, int projectID, String name, String details) {
        String sql = "UPDATE public.interior_design_projects SET project_name = ?, project_details = ? " +
                     "WHERE designer_id = ? AND project_id = ? AND project_status = 'Current'";
        try (Connection c = IDDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, details);
            ps.setString(3, designer);
            ps.setInt(4, projectID);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Update project: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // USER STORY #23: REMOVE CURRENT INTERIOR DESIGN PROJECT — Entity
    // ============================================================
    public static boolean removeProject(String designer, int projectID) {
        String sql = "DELETE FROM public.interior_design_projects " +
                     "WHERE designer_id = ? AND project_id = ? AND project_status = 'Current'";
        try (Connection c = IDDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, designer);
            ps.setInt(2, projectID);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Remove project: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // USER STORY #24: SEARCH CURRENT INTERIOR DESIGN PROJECTS — Entity
    // ============================================================
    public static List<CurrentInteriorDesignProject> searchProjects(String designer, String keyword) {
        List<CurrentInteriorDesignProject> projects = new ArrayList<>();
        String sql = "SELECT project_id, project_name, project_details, designer_id " +
                     "FROM public.interior_design_projects " +
                     "WHERE designer_id = ? AND project_status = 'Current' " +
                     "AND (project_name ILIKE ? OR COALESCE(project_details, '') ILIKE ?) " +
                     "ORDER BY project_id";
        try (Connection c = IDDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            String search = "%" + (keyword == null ? "" : keyword.trim()) + "%";
            ps.setString(1, designer);
            ps.setString(2, search);
            ps.setString(3, search);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    projects.add(new CurrentInteriorDesignProject(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("project_details"),
                        rs.getString("designer_id")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Search projects: " + e.getMessage());
        }
        return projects;
    }
}
