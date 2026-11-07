package com.csit314.platformmanager;
import java.sql.*;
public class DeactivateCategoryController {
    public IDPCategory findCategory(String id)throws SQLException{return IDPCategory.getCategoryDetails(id);}
    public boolean deactivateCategory(String id)throws SQLException{return IDPCategory.setInactive(id);}
}
