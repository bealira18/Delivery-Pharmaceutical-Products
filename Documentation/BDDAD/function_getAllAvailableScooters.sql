CREATE OR REPLACE FUNCTION getAllAvailableScooters(idOrder IN INTEGER)
RETURN SYS_REFCURSOR IS available_scooters SYS_REFCURSOR;

BEGIN

    OPEN available_scooters FOR
        SELECT s.id_scooter FROM scooter s
        INNER JOIN vehicle v
        ON s.id_scooter = v.id_vehicle
        WHERE s.id_scooter NOT IN (SELECT id_vehicle 
                            FROM delivery
                            WHERE delivery_end IS NULL
                            AND id_vehicle IS NOT NULL)
        AND v.id_pharmacy = (SELECT id_pharmacy
                            FROM purchaseOrder
                            WHERE id_order = idOrder);
            
    RETURN available_scooters;

END;