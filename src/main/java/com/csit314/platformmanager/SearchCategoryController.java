package com.csit314.platformmanager;
import java.sql.*;import java.util.*;
public class SearchCategoryController {
    public List<IDPCategory> searchCategories(String keyword)throws SQLException{return IDPCategory.searchCategories(keyword);}
}
