CREATE OR REPLACE FUNCTION getParkById(
idp IN INT) 
RETURN SYS_REFCURSOR IS resultado SYS_REFCURSOR;

BEGIN
    OPEN resultado FOR

    SELECT * FROM park
    WHERE park.id_park = idp;

RETURN (resultado);
END;