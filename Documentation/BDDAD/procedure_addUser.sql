CREATE OR REPLACE PROCEDURE addUser(email_pr IN VARCHAR, password_pr IN VARCHAR, role_pr IN VARCHAR) IS
BEGIN
    INSERT INTO RegisteredUsers(email, password, role)
    VALUES (email_pr, password_pr, role_pr);

--COMMIT;

END;
/
