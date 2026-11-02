package com.csit314.interiordesigner;

import java.util.List;
// USER STORY #24: SEARCH CURRENT PROJECTS — Controller
public class SearchCurrentProjectController {
    public List<CurrentInteriorDesignProject> searchCurrentProjects(String designer,String keyword){return CurrentInteriorDesignProject.searchProjects(designer,keyword);}
}
