# User Story Code Index

Use **Ctrl + Shift + F** in VS Code and search the exact story title (for example, `CREATE USER ACCOUNT`). The `// USER STORY:` comments identify the code for each story in the Boundary, Controller, and Entity files. Existing Java class names and functionality have not been renamed.

| Story | Boundary | Controller | Entity |
|---|---|---|---|
| Login | `UserLoginUI.java` | `UserLoginController.java` | `UserAccount.java` |
| Create User Profile | `UserProfileUI.java` | `UserProfileController.java` | `UserProfile.java` |
| View User Profile | same | same | same (`getProfile`) |
| Update User Profile | same | same | same |
| Suspend User Profile | same | same | same |
| Search User Profile | same | same | same (`getProfile`) |
| Create User Account | `UserAccountUI.java` | `UserAccountController.java` | `UserAccount.java` |
| View User Account | same | same | same (`getAccount`) |
| Update User Account | same | same | same |
| Suspend User Account | same | same | same |
| Search User Account | same | same | same |

**Note:** Shared display methods and `getProfile` are used by more than one story. The comments identify those shared methods. No database schema or method behavior was changed by this labeling pass.
