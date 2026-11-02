package com.csit314.customer;

import java.sql.SQLException;
import java.util.List;

// ============================================================
// USER STORY: SEARCH FAVOURITE PROJECTS — Controller
// ============================================================
public class FavouriteSearchController {
    public List<InteriorDesignProject> searchFavouriteProjects(String userID,String keyword)throws SQLException{
        return FavouriteList.getFavouriteProjects(userID,keyword);
    }
}
