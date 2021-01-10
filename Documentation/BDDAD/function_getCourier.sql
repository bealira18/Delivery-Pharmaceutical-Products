CREATE OR REPLACE FUNCTION getCourier(emailC IN VARCHAR) RETURN
SYS_REFCURSOR IS matching_courier sys_refcursor;

BEGIN
    OPEN matching_courier FOR

    SELECT * FROM courier
    WHERE courier.email = emailC;

RETURN (matching_courier);
END;
/