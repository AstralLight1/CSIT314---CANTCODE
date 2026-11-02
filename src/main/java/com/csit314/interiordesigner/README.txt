INTERIOR DESIGNER MODULE — JAVA 17

Copy all .java files into src/main/java/com/csit314/interiordesigner/ replacing the existing files.

Existing Supabase tables used:
- public.company_information (existing quoted camelCase columns)
- public.interior_design_projects (project_id, designer_id, project_name, project_details, project_status)
- public.customer_favourites (customer_id, project_id)
- public.user_accounts (existing login table)

No new Supabase tables are needed for these features.
Current and Past projects are stored in interior_design_projects with status 'Current' or 'Past'.
Project Statistics reports current/past counts and number of favourite saves.
NOTE: View counts are NOT implemented because there is no view tracking table or column in the supplied schema.
NOTE: Removing a project also removes its customer favourites because of ON DELETE CASCADE.
NOTE: CurrentProjectController and CompanyInformationController are retained for compatibility with existing UI.

Run from Maven project root: mvn compile exec:java
Ensure SUPABASE_JDBC_URL, SUPABASE_DB_USER and SUPABASE_DB_PASSWORD are set in the same terminal.

Test with an Interior Designer account (userType 2).
