package com.csit314.platformmanager;
import java.sql.*;import java.util.*;
public class ViewCategoryController {
    public List<IDPCategory> getCategories()throws SQLException{return IDPCategory.getAllCategories();}
    public IDPCategory getCategoryByID(String id)throws SQLException{return IDPCategory.getCategoryDetails(id);}
}
