package com.csit314.platformmanager;
import java.sql.*;
public class UpdateCategoryController {
    public boolean updateCategory(String id,String name,String description)throws SQLException{return IDPCategory.updateDetails(id,name,description);}
}
