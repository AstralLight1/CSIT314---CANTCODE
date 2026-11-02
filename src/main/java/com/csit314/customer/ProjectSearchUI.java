package com.csit314.customer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;

// ============================================================
// USER STORY: SEARCH INTERIOR DESIGN PROJECTS — Boundary
// ============================================================
public class ProjectSearchUI {
    public void searchProjects(String keyword){
        try{
            List<InteriorDesignProject> found=new ProjectSearchController().searchProjects(keyword);
            if(found.isEmpty())displayNoResults();else displaySearchResults(found);
        }catch(SQLException ex){displayError(ex.getMessage());}
    }
    public void displaySearchResults(List<InteriorDesignProject> projects){
        StringBuilder b=new StringBuilder();for(InteriorDesignProject p:projects)b.append(p).append("\n");
        JOptionPane.showMessageDialog(null,b.toString(),"Project Search Results",JOptionPane.INFORMATION_MESSAGE);
    }
    public void displayNoResults(){JOptionPane.showMessageDialog(null,"No projects found.");}
    public void displayError(String message){JOptionPane.showMessageDialog(null,"Search failed: "+message);}
}
