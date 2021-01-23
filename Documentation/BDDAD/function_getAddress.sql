CREATE OR REPLACE FUNCTION getAddress(ad IN VARCHAR2) RETURN
SYS_REFCURSOR IS resultado SYS_REFCURSOR;

BEGIN
    OPEN resultado FOR

    SELECT * FROM address
    WHERE address.address = ad;

RETURN (resultado);
END;