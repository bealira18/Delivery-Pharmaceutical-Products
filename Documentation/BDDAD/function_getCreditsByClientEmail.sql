CREATE OR REPLACE FUNCTION getCreditsByClientEmail(
    p_clientEmail IN VARCHAR2)
RETURN INTEGER
IS
    does_client_exist INTEGER;
    return_credits INTEGER;
BEGIN
    
    SELECT COUNT(*) INTO does_client_exist
    FROM registeredUser
    WHERE email = p_clientEmail;

    IF does_client_exist = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No client with this email');
    END IF;
    
    SELECT credits INTO return_credits
    FROM client
    WHERE email = p_clientEmail;
    
    RETURN return_credits;
    
END;