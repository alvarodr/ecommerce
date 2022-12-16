-- Roles
INSERT INTO public.roles (id, role_type, description, description_key) VALUES(1, 'SUPERADMIN', 'Superadmin', '-');

-- Users
INSERT INTO public.users("name", surname, email, "password", active, "locked", "role", created_date, code, failed_attemps, owner_company)
VALUES('Alvaro', 'Donaire', 'alvarodr1983@gmail.com', NULL, true, false, 1, '2022-10-30 22:40:05.186', 'adoru3n', 0, -1);

