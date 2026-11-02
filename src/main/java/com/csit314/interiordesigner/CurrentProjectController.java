package com.csit314.interiordesigner;

import java.util.List;
// CONTROLLER: CURRENT PROJECT — Compatibility wrapper for existing UI
public class CurrentProjectController {
    // USER STORY #20: CREATE CURRENT PROJECT
    public boolean createCurrentProject(String designer,String name,String details){return CurrentInteriorDesignProject.createProject(designer,name,details);}
    // USER STORY #21: VIEW CURRENT PROJECTS
    public List<CurrentInteriorDesignProject> viewCurrentProjects(String designer){return CurrentInteriorDesignProject.getCurrentProjects(designer);}
}
