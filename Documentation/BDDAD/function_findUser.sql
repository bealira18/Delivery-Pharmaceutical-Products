CREATE OR REPLACE FUNCTION findUser(
    email_pr IN VARCHAR2, 
    password_pr IN VARCHAR2) RETURN
SYS_REFCURSOR 
IS 
    matching_user SYS_REFCURSOR;

BEGIN
    OPEN matching_user FOR

    SELECT * FROM registeredUser
    WHERE email = email_pr AND password = password_pr;

RETURN (matching_user);
END;