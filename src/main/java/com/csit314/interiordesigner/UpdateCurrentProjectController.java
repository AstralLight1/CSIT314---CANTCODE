package com.csit314.interiordesigner;

// USER STORY #22: UPDATE CURRENT PROJECT — Controller
public class UpdateCurrentProjectController {
    public boolean updateCurrentProject(String designer,int projectID,String name,String details){return CurrentInteriorDesignProject.updateProject(designer,projectID,name,details);}
}
