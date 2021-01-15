CREATE OR REPLACE FUNCTION getProducts
RETURN SYS_REFCURSOR 
IS
    result SYS_REFCURSOR;
BEGIN

    OPEN result FOR
        SELECT *
        FROM product;

    RETURN (result);

END;