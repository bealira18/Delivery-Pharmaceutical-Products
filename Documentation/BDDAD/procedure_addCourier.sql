CREATE OR REPLACE PROCEDURE addCourier(
    p_id_pharmacy IN INTEGER,
    p_email IN courier.email%TYPE,
    p_name IN courier.name%TYPE,
    p_nif IN INTEGER,
    p_social_security IN NUMERIC,
    p_weight IN NUMERIC)
IS
    int_already_registered INTEGER;
BEGIN

    SELECT COUNT(*) INTO int_already_registered
    FROM registeredUser
    WHERE email = p_email;
    
    IF int_already_registered != 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Email already in use');
    END IF;
    
    --default password for couriers is qwerty
    INSERT INTO registeredUser (email, password, role)
    VALUES (p_email, 'qwerty', 'courier');
    
    INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight)
    VALUES(p_email, p_name, p_nif, p_social_security, p_id_pharmacy, p_weight);

END;