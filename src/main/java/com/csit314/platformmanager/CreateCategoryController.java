
package com.csit314.platformmanager;

import java.sql.SQLException;

public class CreateCategoryController {

    // USER STORY 41: CREATE INTERIOR DESIGN PROJECT CATEGORY

    public boolean validateCategory(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean createCategory(String name, String description) throws SQLException {

        if (!validateCategory(name)) {
            return false;
        }

        return IDPCategory.saveCategory(name, description);
    }
}
