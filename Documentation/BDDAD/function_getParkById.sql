create or replace FUNCTION getParkById(idp INT) RETURN
SYS_REFCURSOR is resultado sys_refcursor;

BEGIN
    OPEN resultado FOR

    SELECT * FROM park
    WHERE park.id_park = idp;

RETURN (resultado);
END;
/