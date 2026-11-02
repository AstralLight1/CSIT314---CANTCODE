# CSIT314 Interior Designer integration

Includes stories #13, #14, #15, #16, #17, #19, #20 and #21 in package `com.csit314.interiordesigner`.

1. Copy the contents of `src/main/java/com/csit314/interiordesigner` into the same folder in your EXISTING Maven project. **Do not copy the entire project over your working one.**
2. Replace your existing `UserLoginUI.java` with the version in this ZIP (or apply only the role routing changes). Existing `UserLoginController.java` stays the same.
3. Run `interior_designer_setup.sql` in Supabase SQL Editor. It creates two new tables without deleting existing data.
4. Create `designer01` using User Admin > Create User Account with userType 2, status Active and a password of your choice.
5. Use your existing Supabase environment variables in the SAME PowerShell terminal and run `mvn compile exec:java` from the directory containing pom.xml.
6. Login as designer01 with Interior Designer selected.

The same `user_accounts` table handles authentication. Company and project data are scoped to the logged-in designer ID. Logout clears local session state and returns to the shared login screen. There is no server-side session table.

Note: Your diagrams vary in class names for view company information and use a different set of account attributes for some stories; this implementation uses one shared CompanyInformation BCE trio and the working user_accounts schema. Test all UI flows and database connection before submitting.
