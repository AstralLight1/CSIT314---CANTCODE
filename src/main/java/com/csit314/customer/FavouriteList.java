package com.csit314.customer;

import java.sql.*;
import java.util.*;

// ============================================================
// ENTITY: FAVOURITE LIST — Save, Search and View
// ============================================================
public class FavouriteList {
    // USER STORY: SAVE PROJECT TO FAVOURITE LIST — Entity
    // Returns false for an already-saved project; SQL errors propagate to UI.
    public static boolean addFavourite(String userID,String projectID)throws SQLException{
        String sql="INSERT INTO public.customer_favourites(customer_id,project_id) VALUES(?,?::bigint) ON CONFLICT(customer_id,project_id) DO NOTHING";
        try(Connection c=CustomerDatabase.connect();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,userID);ps.setString(2,projectID);
            return ps.executeUpdate()==1;
        }
    }
    // USER STORY: VIEW FAVOURITE PROJECTS — Entity
    public static List<InteriorDesignProject> getFavouriteProjects(String userID)throws SQLException{
        return getFavouriteProjects(userID,"");
    }
    // USER STORY: SEARCH FAVOURITE PROJECTS — Entity
    public static List<InteriorDesignProject> getFavouriteProjects(String userID,String keyword)throws SQLException{
        String sql="SELECT p.project_id,p.project_name,p.project_details,p.designer_id FROM public.customer_favourites f JOIN public.interior_design_projects p ON p.project_id=f.project_id WHERE f.customer_id=? AND (p.project_name ILIKE ? OR p.project_details ILIKE ?) ORDER BY p.project_name";
        List<InteriorDesignProject> out=new ArrayList<>();
        try(Connection c=CustomerDatabase.connect();PreparedStatement ps=c.prepareStatement(sql)){
            String q="%"+(keyword==null?"":keyword.trim())+"%";
            ps.setString(1,userID);ps.setString(2,q);ps.setString(3,q);
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next())out.add(new InteriorDesignProject(rs.getString("project_id"),rs.getString("project_name"),rs.getString("project_details"),rs.getString("designer_id")));
            }
        }
        return out;
    }
}
