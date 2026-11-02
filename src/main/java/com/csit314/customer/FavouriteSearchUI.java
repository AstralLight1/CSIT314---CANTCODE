package com.csit314.customer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;

// ============================================================
// USER STORY: SEARCH FAVOURITE PROJECTS — Boundary
// ============================================================
public class FavouriteSearchUI {
    public void searchFavouriteProjects(String keyword){
        String userID=CustomerSession.getUserID();
        if(userID==null){displayError("Not logged in");return;}
        try{
            List<InteriorDesignProject> list=new FavouriteSearchController().searchFavouriteProjects(userID,keyword);
            if(list.isEmpty())displayNoResultsMessage();else displaySearchResults(list);
        }catch(SQLException ex){displayError(ex.getMessage());}
    }
    public void displaySearchResults(List<InteriorDesignProject> projects){
        StringBuilder b=new StringBuilder();for(InteriorDesignProject p:projects)b.append(p).append("\n");
        JOptionPane.showMessageDialog(null,b.toString(),"Favourite Search",JOptionPane.INFORMATION_MESSAGE);
    }
    public void displayNoResultsMessage(){JOptionPane.showMessageDialog(null,"No matching favourites.");}
    public void displayError(String message){JOptionPane.showMessageDialog(null,"Search failed: "+message);}
}
