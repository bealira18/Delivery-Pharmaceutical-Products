create or replace FUNCTION getCourier(emailC VARCHAR) RETURN
SYS_REFCURSOR is resultado sys_refcursor;

BEGIN
    OPEN resultado FOR

    SELECT * FROM courier
    WHERE courier.email = emailC;

RETURN (resultado);
END;
/