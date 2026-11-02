package com.csit314.interiordesigner;

// USER STORY #27: UPDATE PAST PROJECT — Controller
public class UpdatePastProjectController {
    public boolean updatePastProject(String designer, int projectID, String name, String details) {
        return PastInteriorDesignProject.updatePastProject(designer, projectID, name, details);
    }
}
