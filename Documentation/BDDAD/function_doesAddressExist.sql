CREATE OR REPLACE FUNCTION DOESADDRESSEXIST(a_name IN VARCHAR2)
RETURN BOOLEAN AS

    result      INTEGER;

BEGIN

    SELECT COUNT(*) INTO result
    FROM ADDRESS
    WHERE ADDRESS = a_name;

    RETURN result > 0;
END;