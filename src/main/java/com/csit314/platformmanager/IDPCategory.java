package com.csit314.platformmanager;
import java.sql.*;
import java.util.*;
public class IDPCategory {
    private final String categoryID,categoryName,description;
    private final boolean isActive;
    public IDPCategory(String id,String name,String description,boolean active){this.categoryID=id;this.categoryName=name;this.description=description;this.isActive=active;}
    public String getCategoryID(){return categoryID;}
    public String getCategoryName(){return categoryName;}
    public String getDescription(){return description;}
    public boolean isActive(){return isActive;}
    public String toString(){return categoryID+" | "+categoryName+" | "+description+" | "+(isActive?"Active":"Inactive");}
    private static IDPCategory from(ResultSet r)throws SQLException{return new IDPCategory(r.getString("category_id"),r.getString("category_name"),r.getString("description"),r.getBoolean("is_active"));}
    public static boolean saveCategory(String name,String description)throws SQLException{
        String sql="INSERT INTO public.idp_categories (category_name,description) VALUES (?,?)";
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,name);p.setString(2,description);return p.executeUpdate()>0;}
    }
    public static List<IDPCategory> getAllCategories()throws SQLException{
        List<IDPCategory> list=new ArrayList<>();
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement("SELECT category_id,category_name,description,is_active FROM public.idp_categories ORDER BY category_id");ResultSet r=p.executeQuery()){
            while(r.next())list.add(from(r));
        }return list;
    }
    public static IDPCategory getCategoryDetails(String id)throws SQLException{
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement("SELECT category_id,category_name,description,is_active FROM public.idp_categories WHERE category_id=?::bigint")){
            p.setString(1,id);try(ResultSet r=p.executeQuery()){return r.next()?from(r):null;}
        }
    }
    public static boolean updateDetails(String id,String name,String description)throws SQLException{
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement("UPDATE public.idp_categories SET category_name=?,description=? WHERE category_id=?::bigint")){
            p.setString(1,name);p.setString(2,description);p.setString(3,id);return p.executeUpdate()>0;
        }
    }
    public static boolean setInactive(String id)throws SQLException{
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement("UPDATE public.idp_categories SET is_active=false WHERE category_id=?::bigint")){
            p.setString(1,id);return p.executeUpdate()>0;
        }
    }
    public static List<IDPCategory> searchCategories(String keyword)throws SQLException{
        List<IDPCategory> list=new ArrayList<>();
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement("SELECT category_id,category_name,description,is_active FROM public.idp_categories WHERE category_name ILIKE ? OR description ILIKE ? ORDER BY category_id")){
            String q="%"+keyword+"%";p.setString(1,q);p.setString(2,q);
            try(ResultSet r=p.executeQuery()){while(r.next())list.add(from(r));}
        }return list;
    }
}
