package com.csit314.interiordesigner;

import javax.swing.*;
import java.util.List;

// USER STORIES #25–#28: PAST PROJECTS — Boundary
public class PastProjectUI {
    private final String designer;
    public PastProjectUI(String designer) { this.designer = designer; }

    public void displayMenu() {
        String[] choices = {"Create Past Project", "View Past Projects", "Update Past Project", "Remove Past Project", "Search Past Projects"};
        String choice = (String) JOptionPane.showInputDialog(null, "Past Projects", "Interior Designer", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
        if ("Create Past Project".equals(choice)) createPastProject();
        else if ("View Past Projects".equals(choice)) viewPastProjects();
        else if ("Update Past Project".equals(choice)) updatePastProject();
        else if ("Remove Past Project".equals(choice)) removePastProject();
        else if ("Search Past Projects".equals(choice)) searchPastProjects();
    }

    // USER STORY #25: CREATE PAST PROJECT — Boundary
    public void createPastProject() {
        String name = JOptionPane.showInputDialog("Past project name:");
        if (name == null) return;
        String details = JOptionPane.showInputDialog("Past project details:");
        if (details == null) return;
        if (name.isBlank()) { JOptionPane.showMessageDialog(null, "Project name required."); return; }
        boolean ok = new CreatePastProjectController().createPastProject(designer, name, details);
        JOptionPane.showMessageDialog(null, ok ? "Past project created!" : "Past project creation failed.");
    }

    // USER STORY #26: VIEW PAST PROJECTS — Boundary
    public void viewPastProjects() {
        List<CurrentInteriorDesignProject> list = PastInteriorDesignProject.viewPastProjects(designer);
        if (list.isEmpty()) { JOptionPane.showMessageDialog(null, "No past projects found."); return; }
        StringBuilder b = new StringBuilder();
        for (CurrentInteriorDesignProject p : list)
            b.append("ID: ").append(p.getProjectID()).append(" | ").append(p.getProjectName()).append("\n").append(p.getProjectDetails()).append("\n\n");
        JTextArea a = new JTextArea(b.toString(), 15, 45);
        a.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(a), "Past Projects", JOptionPane.INFORMATION_MESSAGE);
    }

    // USER STORY #27: UPDATE PAST PROJECT — Boundary
    public void updatePastProject() {
        String idText = JOptionPane.showInputDialog("Past project ID to update:");
        if (idText == null) return;
        int projectID;
        try { projectID = Integer.parseInt(idText.trim()); }
        catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "Invalid Project ID."); return; }
        String name = JOptionPane.showInputDialog("New past project name:");
        if (name == null) return;
        String details = JOptionPane.showInputDialog("New past project details:");
        if (details == null) return;
        if (name.isBlank()) { JOptionPane.showMessageDialog(null, "Project name required."); return; }
        boolean ok = new UpdatePastProjectController().updatePastProject(designer, projectID, name, details);
        JOptionPane.showMessageDialog(null, ok ? "Past project updated!" : "Past project update failed or project not found.");
    }

    // USER STORY #28: REMOVE PAST PROJECT — Boundary
    public void removePastProject() {
        String idText = JOptionPane.showInputDialog("Past project ID to remove:");
        if (idText == null) return;
        int projectID;
        try { projectID = Integer.parseInt(idText.trim()); }
        catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "Invalid Project ID."); return; }
        int confirm = JOptionPane.showConfirmDialog(null, "Remove past project ID " + projectID + "?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        boolean ok = new RemovePastProjectController().removePastProject(designer, projectID);
        JOptionPane.showMessageDialog(null, ok ? "Past project removed!" : "Past project removal failed or project not found.");
    }
    // USER STORY #29: SEARCH PAST PROJECTS — Boundary
    public void searchPastProjects() {
        String keyword = JOptionPane.showInputDialog("Search past projects by keyword:");
        if (keyword == null) return;
        List<CurrentInteriorDesignProject> list = new SearchPastProjectController().searchPastProjects(designer, keyword);
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No matching past projects found.");
            return;
        }
        StringBuilder b = new StringBuilder();
        for (CurrentInteriorDesignProject p : list)
            b.append("ID: ").append(p.getProjectID()).append(" | ").append(p.getProjectName())
             .append("\n").append(p.getProjectDetails()).append("\n\n");
        JTextArea a = new JTextArea(b.toString(), 15, 45);
        a.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(a), "Search Past Projects", JOptionPane.INFORMATION_MESSAGE);
    }
}
