package com.csit314.interiordesigner;

import java.sql.*;
import java.util.*;

// ENTITY: PAST INTERIOR DESIGN PROJECT
public class PastInteriorDesignProject {
    // USER STORY #25: CREATE PAST PROJECT — Entity
    public static boolean createPastProject(String designer, String name, String details) {
        String sql = "INSERT INTO public.interior_design_projects(designer_id,project_name,project_details,project_status) VALUES (?,?,?,'Past')";
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, designer);
            p.setString(2, name);
            p.setString(3, details);
            return p.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Create past project: " + e.getMessage());
            return false;
        }
    }

    // USER STORY #26: VIEW PAST PROJECTS — Entity
    public static List<CurrentInteriorDesignProject> viewPastProjects(String designer) {
        List<CurrentInteriorDesignProject> list = new ArrayList<>();
        String sql = "SELECT project_id,project_name,project_details,designer_id FROM public.interior_design_projects WHERE designer_id=? AND project_status='Past' ORDER BY project_id";
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, designer);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    list.add(new CurrentInteriorDesignProject(r.getInt(1), r.getString(2), r.getString(3), r.getString(4)));
                }
            }
        } catch (SQLException e) {
            System.err.println("View past projects: " + e.getMessage());
        }
        return list;
    }

    // USER STORY #27: UPDATE PAST PROJECT — Entity
    public static boolean updatePastProject(String designer, int projectID, String name, String details) {
        String sql = "UPDATE public.interior_design_projects SET project_name=?, project_details=? WHERE designer_id=? AND project_id=? AND project_status='Past'";
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, name);
            p.setString(2, details);
            p.setString(3, designer);
            p.setInt(4, projectID);
            return p.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Update past project: " + e.getMessage());
            return false;
        }
    }

    // USER STORY #28: REMOVE PAST PROJECT — Entity
    public static boolean removePastProject(String designer, int projectID) {
        String sql = "DELETE FROM public.interior_design_projects WHERE designer_id=? AND project_id=? AND project_status='Past'";
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, designer);
            p.setInt(2, projectID);
            return p.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Remove past project: " + e.getMessage());
            return false;
        }
    }
    // USER STORY #29: SEARCH PAST PROJECTS — Entity
    public static List<CurrentInteriorDesignProject> searchPastProjects(String designer, String keyword) {
        List<CurrentInteriorDesignProject> list = new ArrayList<>();
        String sql = "SELECT project_id,project_name,project_details,designer_id " +
                "FROM public.interior_design_projects " +
                "WHERE designer_id=? AND project_status='Past' " +
                "AND (project_name ILIKE ? OR COALESCE(project_details,'') ILIKE ?) " +
                "ORDER BY project_id";
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            String search = "%" + keyword.trim() + "%";
            p.setString(1, designer);
            p.setString(2, search);
            p.setString(3, search);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    list.add(new CurrentInteriorDesignProject(r.getInt(1), r.getString(2), r.getString(3), r.getString(4)));
                }
            }
        } catch (SQLException e) {
            System.err.println("Search past projects: " + e.getMessage());
        }
        return list;
    }
}
