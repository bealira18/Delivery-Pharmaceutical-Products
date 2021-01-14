CREATE OR REPLACE PROCEDURE addClientCredits(email_pr VARCHAR2, credits_pr INT)
IS
    DOES_USER_EXIST INTEGER;
BEGIN
    SELECT COUNT(*) INTO DOES_USER_EXIST
    FROM registeredUser
    WHERE email = email_pr;

    IF DOES_USER_EXIST = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'This user does not exist');
    END IF;

    UPDATE CLIENT
    SET CREDITS = CREDITS + credits_pr
    WHERE EMAIL = email_pr;
END;