CREATE OR REPLACE PROCEDURE updateCreditsClient(
    p_emailClient IN VARCHAR2,
    p_newCreditAmount IN INTEGER)
IS
    does_client_exist INTEGER;
BEGIN
    
    SELECT COUNT(*) INTO does_client_exist
    FROM registeredUser
    WHERE email = p_emailClient;

    IF does_client_exist = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No client with this email');
    END IF;
    
    UPDATE client SET credits = p_newCreditAmount
    WHERE email = p_emailClient;
    
END;