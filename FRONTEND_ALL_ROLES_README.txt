CSIT314 - CONSISTENT FRONTEND FOR ALL FOUR ROLES

This update extends the shared Swing presentation theme to all existing role screens:
- User Admin (menus, account/profile forms and dialogs)
- Interior Designer (menus, project/company forms and dialogs)
- Customer (menus, project/favourite forms and dialogs)
- Platform Manager (menus, categories and report screens)

Presentation updates: consistent blue/white colors, typography, input borders,
button styling, table rows and headers, scroll panes, dialogs and spacing where
supported by the existing layouts. Existing login screen design is retained.

No Controller, Entity, SQL or database logic was modified. All existing button
listeners, workflows and navigation remain in place. This is a shared visual
refresh of existing screens, not a replacement of every individual layout.

To run:
  cd CSIT314-UserAdmin
  (set Supabase environment variables in this same terminal)
  mvn compile exec:java

The shared UiTheme.java was compiled with javac 17 successfully. Full Maven
application build was not run here because Maven/dependency cache is unavailable.
