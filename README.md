# CSIT314 Unified User Admin System

This is an update to the earlier combined Maven project. It keeps Login, Create/View/Update/Suspend Profile and adds Search Profile plus Create/View/Update/Suspend/Search Account.

## Run
Open the folder containing `pom.xml` in VS Code. Set these environment variables **in the same terminal** (values from Supabase Connect > Session Pooler; do not share credentials):

```powershell
$env:SUPABASE_JDBC_URL = "jdbc:postgresql://YOUR_POOLER_HOST:5432/postgres"
$env:SUPABASE_DB_USER = "YOUR_DATABASE_USER"
$env:SUPABASE_DB_PASSWORD = "YOUR_DATABASE_PASSWORD"
mvn compile exec:java
```

Use the actual port displayed by Supabase (session pooler often uses 5432). Do not commit passwords to Git.

## Tables
Uses the existing `public.user_accounts` columns `"userID"` (varchar), `"passwordHash"` (varchar), `"userType"` (int4), `"accountStatus"` (varchar), and `public.user_profiles` columns `"userID"`, `"fullName"`, `email`, `"phoneNumber"`, `status`. Run the existing `setup.sql` only if you have not yet created the profile table. Existing tables do not need to be recreated.

## Notes
- User type: 1 User Admin, 2 Interior Designer, 3 Customer, 4 Platform Management.
- Creating/updating an account hashes a newly supplied password with BCrypt; a blank password on update keeps the existing hash. The account screen never displays password hashes.
- Search Account returns the first matching User ID (the BCE returns a single UserAccount). Search Profile uses exact User ID (the BCE calls getProfile(userID)).
- Suspend Account changes `accountStatus`; Suspend Profile changes `status`. They are distinct operations, matching your diagrams.
- Existing accounts must have BCrypt hashes to log in. A profile needs a matching account if your existing table has a foreign key.
- Your supplied account diagrams disagree on fields (some show username/email, others show passwordHash/userType). This code preserves your **actual Supabase user_accounts schema** to avoid breaking Login.
- This code has not been live-tested against your Supabase instance.

## Finding code by user story
See `USER_STORY_INDEX.md`. Search for `USER STORY:` in VS Code.
