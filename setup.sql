-- Run only if you have not already created user_profiles.
CREATE TABLE IF NOT EXISTS public.user_profiles (
    "userID" varchar PRIMARY KEY REFERENCES public.user_accounts("userID"),
    "fullName" varchar,
    email varchar,
    "phoneNumber" varchar,
    status varchar DEFAULT 'Active'
);
ALTER TABLE public.user_profiles ADD COLUMN IF NOT EXISTS status varchar DEFAULT 'Active';
