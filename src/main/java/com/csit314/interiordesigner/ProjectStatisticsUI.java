package com.csit314.interiordesigner;

import javax.swing.*;
import java.sql.SQLException;

// BOUNDARY: PROJECT STATISTICS
public class ProjectStatisticsUI {
    private final String designer;
    public ProjectStatisticsUI(String designer) { this.designer = designer; }

    public void viewProjectStatistics() {
        try {
            ProjectStatistics s = new ProjectStatisticsController().viewProjectStatistics(designer);
            String views = ProjectStatistics.getProjectViewCounts(designer);
            JTextArea area = new JTextArea(
                "Current projects: " + s.getCurrentProjects() +
                "\nPast projects: " + s.getPastProjects() +
                "\nTimes projects saved to favourites: " + s.getFavourites() +
                "\n\nProject view counts and shortlists:\n" + views,
                15, 48
            );
            area.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(area),
                "Project Statistics", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to load statistics: " + e.getMessage());
        }
    }
}
