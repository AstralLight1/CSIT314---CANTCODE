package com.csit314.customer;

import java.sql.SQLException;
import java.util.List;

// ============================================================
// USER STORY: SAVE PROJECT / VIEW FAVOURITES — Controller
// ============================================================
public class FavouriteController {
    public boolean saveFavourite(String userID,String projectID)throws SQLException{
        return FavouriteList.addFavourite(userID,projectID);
    }
    public List<InteriorDesignProject> getFavouriteProjects(String userID)throws SQLException{
        return FavouriteList.getFavouriteProjects(userID);
    }
}
