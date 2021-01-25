CREATE OR REPLACE PROCEDURE addAdministrator(
    p_email IN VARCHAR2,
    p_idPharmacy IN INTEGER, 
    p_name IN VARCHAR2, 
    p_nif IN INTEGER, 
    p_social_security IN NUMERIC) 
IS
    int_already_registered INTEGER;
BEGIN

    SELECT COUNT(*) INTO int_already_registered
    FROM registeredUser
    WHERE email = p_email;
    
    IF int_already_registered != 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Email already in use');
    END IF;
    
    --default password for administrators is qwerty
    INSERT INTO registeredUser (email, password, role)
    VALUES (p_email, 'qwerty', 'administrator');

    INSERT INTO administrator(email, id_pharmacy, name, nif, social_security)
    VALUES(p_email, p_idPharmacy, p_name, p_nif, p_social_security);

END;