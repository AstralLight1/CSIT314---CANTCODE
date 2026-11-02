package com.csit314.customer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;

// ============================================================
// USER STORIES: SAVE PROJECT / VIEW FAVOURITES — Boundary
// ============================================================
public class FavouriteUI {
    public void saveProject(String projectID){
        String userID=CustomerSession.getUserID();
        if(userID==null){displayError("Not logged in");return;}
        try{
            if(new FavouriteController().saveFavourite(userID,projectID))displaySuccess();
            else displayAlreadySavedMessage();
        }catch(SQLException ex){displayError(ex.getMessage());}
    }
    public void viewFavouriteProjects(){
        String userID=CustomerSession.getUserID();
        if(userID==null){displayError("Not logged in");return;}
        try{
            List<InteriorDesignProject> list=new FavouriteController().getFavouriteProjects(userID);
            if(list.isEmpty())displayNoFavouritesMessage();else displayFavouriteProjects(list);
        }catch(SQLException ex){displayError(ex.getMessage());}
    }
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Project saved to favourites.");}
    public void displayAlreadySavedMessage(){JOptionPane.showMessageDialog(null,"Project already saved.");}
    public void displayFavouriteProjects(List<InteriorDesignProject> projects){
        StringBuilder b=new StringBuilder();for(InteriorDesignProject p:projects)b.append(p).append("\n");
        JOptionPane.showMessageDialog(null,b.toString(),"My Favourites",JOptionPane.INFORMATION_MESSAGE);
    }
    public void displayNoFavouritesMessage(){JOptionPane.showMessageDialog(null,"No favourite projects.");}
    public void displayError(String message){JOptionPane.showMessageDialog(null,"Favourite operation failed: "+message);}
}
