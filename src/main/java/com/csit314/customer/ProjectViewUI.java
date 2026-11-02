package com.csit314.customer;

import javax.swing.*;
import java.sql.SQLException;

// ============================================================
// USER STORY: VIEW INTERIOR DESIGN PROJECT — Boundary
// ============================================================
public class ProjectViewUI {
    public void viewProjectDetails(String projectID){
        try{
            InteriorDesignProject p=new ProjectViewController().getProjectDetails(projectID);
            if(p==null)displayNotFoundMessage();else displayProjectDetails(p);
        }catch(SQLException ex){displayError(ex.getMessage());}
    }
    public void displayProjectDetails(InteriorDesignProject p){
        JOptionPane.showMessageDialog(null,"ID: "+p.getProjectID()+"\nName: "+p.getProjectName()+"\nDetails: "+p.getProjectDetails()+"\nDesigner: "+p.getDesignerID());
    }
    public void displayNotFoundMessage(){JOptionPane.showMessageDialog(null,"Project not found.");}
    public void displayError(String message){JOptionPane.showMessageDialog(null,"View failed: "+message);}
}
