CREATE OR REPLACE FUNCTION getPaths
RETURN SYS_REFCURSOR
IS
    result  SYS_REFCURSOR;
BEGIN

    OPEN result FOR
        SELECT *
        FROM path;

    RETURN result;
END;