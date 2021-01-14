create or replace FUNCTION getAddress(ad VARCHAR) RETURN
SYS_REFCURSOR is resultado sys_refcursor;

BEGIN
    OPEN resultado FOR

    SELECT * FROM address
    WHERE address.address = ad;

RETURN (resultado);
END;
/