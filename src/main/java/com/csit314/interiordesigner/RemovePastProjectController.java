package com.csit314.interiordesigner;

// USER STORY #28: REMOVE PAST PROJECT — Controller
public class RemovePastProjectController {
    public boolean removePastProject(String designer, int projectID) {
        return PastInteriorDesignProject.removePastProject(designer, projectID);
    }
}
