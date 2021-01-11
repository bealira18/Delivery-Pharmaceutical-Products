CREATE OR REPLACE FUNCTION getNextAvailableCourier(idPharmacy IN INTEGER) 
RETURN SYS_REFCURSOR is next_courier sys_refcursor;

BEGIN

    OPEN next_courier FOR
    
        SELECT email_courier
        FROM delivery
        WHERE delivery_end > sysdate
        AND email_courier IN (SELECT email FROM courier
                              WHERE id_pharmacy = idPharmacy)
        ORDER BY delivery_end
        FETCH FIRST 1 ROW ONLY;
            
    RETURN next_courier;

END;