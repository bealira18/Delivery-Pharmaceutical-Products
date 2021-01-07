CREATE OR REPLACE FUNCTION findUser(email_pr VARCHAR, password_pr VARCHAR) RETURN
SYS_REFCURSOR IS MATCHING_USER sys_refcursor;

BEGIN
    OPEN MATCHING_USER FOR

    SELECT * FROM RegisteredUsers
    WHERE email = email_pr AND password = password_pr;

RETURN (MATCHING_USER);
END;
/
