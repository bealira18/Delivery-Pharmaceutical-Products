CREATE OR REPLACE FUNCTION getNextAvailableCourier(
    idPharmacy IN INTEGER) 
RETURN SYS_REFCURSOR 
IS 
    next_courier SYS_REFCURSOR; 
BEGIN
    OPEN next_courier FOR
        SELECT *
        FROM delivery
        WHERE delivery_end > sysdate
        AND email_courier IN (SELECT email FROM courier
                              WHERE id_pharmacy = idPharmacy)
        ORDER BY delivery_end
        FETCH FIRST 1 ROW ONLY;
    
    RETURN next_courier;
    
END;