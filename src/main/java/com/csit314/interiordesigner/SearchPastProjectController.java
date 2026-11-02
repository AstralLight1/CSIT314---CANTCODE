package com.csit314.interiordesigner;

import java.util.List;

// USER STORY #29: SEARCH PAST PROJECTS — Controller
public class SearchPastProjectController {
    public List<CurrentInteriorDesignProject> searchPastProjects(String designer, String keyword) {
        return PastInteriorDesignProject.searchPastProjects(designer, keyword);
    }
}
