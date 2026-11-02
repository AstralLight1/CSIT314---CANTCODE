package com.csit314.customer;

import java.sql.SQLException;
import java.util.List;

// ============================================================
// USER STORY: SEARCH INTERIOR DESIGN PROJECTS — Controller
// ============================================================
public class ProjectSearchController {
    public List<InteriorDesignProject> searchProjects(String keyword)throws SQLException{
        return InteriorDesignProject.searchProjects(keyword);
    }
}
