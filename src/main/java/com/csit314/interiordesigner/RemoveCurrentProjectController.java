package com.csit314.interiordesigner;

// USER STORY #23: REMOVE CURRENT PROJECT — Controller
public class RemoveCurrentProjectController {
    public boolean removeCurrentProject(String designer,int projectID){return CurrentInteriorDesignProject.removeProject(designer,projectID);}
}
