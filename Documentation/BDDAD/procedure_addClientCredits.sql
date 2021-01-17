CREATE OR REPLACE PROCEDURE addClientCredits(
    email_pr IN VARCHAR2, 
    credits_pr IN INTEGER)
IS
    does_user_exist INTEGER;
BEGIN
    SELECT COUNT(*) INTO does_user_exist
    FROM registeredUser
    WHERE email = email_pr;

    IF does_user_exist = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'This user does not exist');
    END IF;

    UPDATE client
    SET credits = credits + credits_pr
    WHERE email = email_pr;
END;