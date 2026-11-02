package com.csit314.customer;

import java.sql.SQLException;

// ============================================================
// USER STORY: VIEW INTERIOR DESIGN PROJECT — Controller
// ============================================================
public class ProjectViewController {
    public InteriorDesignProject getProjectDetails(String projectID)throws SQLException{
        return InteriorDesignProject.getProjectDetails(projectID);
    }
}
