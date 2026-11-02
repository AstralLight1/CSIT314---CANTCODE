package com.csit314.interiordesigner;

import java.sql.*;

// ENTITY: PROJECT STATISTICS — Counts from existing Supabase tables
public class ProjectStatistics {
    private final int currentProjects, pastProjects, favourites;
    public ProjectStatistics(int currentProjects, int pastProjects, int favourites) {
        this.currentProjects = currentProjects;
        this.pastProjects = pastProjects;
        this.favourites = favourites;
    }
    public int getCurrentProjects() { return currentProjects; }
    public int getPastProjects() { return pastProjects; }
    public int getFavourites() { return favourites; }

    // Existing statistics — unchanged
    public static ProjectStatistics getStatistics(String designer) throws SQLException {
        String sql = "SELECT " +
            "(SELECT COUNT(*) FROM public.interior_design_projects WHERE designer_id=? AND project_status='Current') AS current_count," +
            "(SELECT COUNT(*) FROM public.interior_design_projects WHERE designer_id=? AND project_status='Past') AS past_count," +
            "(SELECT COUNT(*) FROM public.customer_favourites f JOIN public.interior_design_projects p ON p.project_id=f.project_id WHERE p.designer_id=?) AS favourite_count";
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, designer);
            p.setString(2, designer);
            p.setString(3, designer);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) return new ProjectStatistics(r.getInt("current_count"), r.getInt("past_count"), r.getInt("favourite_count"));
            }
        }
        return new ProjectStatistics(0, 0, 0);
    }

    // USER STORIES #37 AND #38: VIEW PROJECT VIEWS AND SHORTLISTS — Entity
    public static String getProjectViewCounts(String designer) throws SQLException {
        String sql = "SELECT p.project_id, p.project_name, p.view_count, " +
                     "COUNT(f.favourite_id) AS shortlist_count " +
                     "FROM public.interior_design_projects p " +
                     "LEFT JOIN public.customer_favourites f ON f.project_id = p.project_id " +
                     "WHERE p.designer_id = ? " +
                     "GROUP BY p.project_id, p.project_name, p.view_count " +
                     "ORDER BY p.project_id";
        StringBuilder result = new StringBuilder();
        try (Connection c = IDDatabase.connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, designer);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    result.append("ID: ").append(r.getLong("project_id"))
                          .append(" | ").append(r.getString("project_name"))
                          .append("\nViews: ").append(r.getInt("view_count"))
                          .append(" | Shortlisted: ").append(r.getLong("shortlist_count"))
                          .append("\n\n");
                }
            }
        }
        return result.length() == 0 ? "No projects found." : result.toString();
    }
}
