CREATE OR REPLACE FUNCTION getAllAvailableCouriers(idOrder IN INTEGER)
RETURN SYS_REFCURSOR IS available_couriers SYS_REFCURSOR;

BEGIN

    OPEN available_couriers FOR
        SELECT email FROM courier
        WHERE email NOT IN ( SELECT email_courier 
                            FROM delivery
                            WHERE delivery_end IS NULL
                            AND email_courier IS NOT NULL)
        AND id_pharmacy = (SELECT id_pharmacy
                            FROM purchaseOrder
                            WHERE id_order = idOrder);
            
    RETURN available_couriers;

END;