CREATE OR REPLACE FUNCTION doesAddressExist(
    a_name IN address.address%TYPE)
RETURN INTEGER 
IS
    result      INTEGER;
BEGIN

    SELECT COUNT(*) INTO result
    FROM address
    WHERE address = a_name;
    
    IF result > 0 THEN
        RETURN 0;
    ELSE
        RETURN 1;
    END IF;
END;