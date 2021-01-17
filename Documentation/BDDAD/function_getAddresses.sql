CREATE OR REPLACE FUNCTION getAddresses
RETURN SYS_REFCURSOR 
IS
    result      SYS_REFCURSOR;

BEGIN

    OPEN result FOR
        SELECT *
        FROM address;

    RETURN result;
END;