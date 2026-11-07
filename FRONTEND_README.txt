CSIT314 Java Swing frontend update

Implemented: shared UI styling, main login redesign, and four role dashboard/menu layouts.
All existing feature Boundaries, Controllers, Entities, and Supabase queries are preserved.
This is a first-stage wireframe implementation, NOT a complete pixel-for-pixel implementation of all 42 PDF pages.

Open the folder containing pom.xml in VS Code.
Run: mvn compile exec:java
Supabase environment variables remain necessary for login and database actions.

Modified: Main.java, UserLoginUI.java, UserAdminMenu.java, customer/CustomerMenu.java, interiordesigner/InteriorDesignerMenu.java, platformmanager/PlatformManagerMenu.java.
Added: ui/UiTheme.java.
