package com.csit314.customer;

import java.sql.*;
import java.util.*;

// ============================================================
// ENTITY: INTERIOR DESIGN PROJECT — Search and View
// ============================================================
public class InteriorDesignProject {
    private final String projectID,projectName,projectDetails,designerID;
    public InteriorDesignProject(String id,String name,String details,String designer){
        projectID=id;projectName=name;projectDetails=details;designerID=designer;
    }
    public String getProjectID(){return projectID;}
    public String getProjectName(){return projectName;}
    public String getProjectDetails(){return projectDetails;}
    public String getDesignerID(){return designerID;}
    public String toString(){return projectName+" ("+projectID+")";}
    private static InteriorDesignProject from(ResultSet r)throws SQLException{
        return new InteriorDesignProject(r.getString("project_id"),r.getString("project_name"),r.getString("project_details"),r.getString("designer_id"));
    }
    // USER STORY: SEARCH INTERIOR DESIGN PROJECTS — Entity
    public static List<InteriorDesignProject> searchProjects(String keyword)throws SQLException{
        String sql="SELECT project_id,project_name,project_details,designer_id FROM public.interior_design_projects WHERE project_name ILIKE ? OR project_details ILIKE ? ORDER BY project_name";
        List<InteriorDesignProject> out=new ArrayList<>();
        try(Connection c=CustomerDatabase.connect();PreparedStatement ps=c.prepareStatement(sql)){
            String q="%"+(keyword==null?"":keyword.trim())+"%";
            ps.setString(1,q);ps.setString(2,q);
            try(ResultSet rs=ps.executeQuery()){while(rs.next())out.add(from(rs));}
        }
        return out;
    }
    // USER STORY: VIEW INTERIOR DESIGN PROJECT — Entity
    // Record one view only when the requested project exists.
    public static InteriorDesignProject getProjectDetails(String id)throws SQLException{
        String sql="UPDATE public.interior_design_projects " +
                   "SET view_count = view_count + 1 " +
                   "WHERE project_id = ?::bigint " +
                   "RETURNING project_id,project_name,project_details,designer_id";
        try(Connection c=CustomerDatabase.connect();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,id);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next()?from(rs):null;
            }
        }
    }
}
