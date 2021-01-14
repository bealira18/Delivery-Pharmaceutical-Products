CREATE OR REPLACE FUNCTION getAllAvailableDrones(idOrder IN INTEGER)
RETURN SYS_REFCURSOR IS available_drones SYS_REFCURSOR;

BEGIN

    OPEN available_drones FOR
        SELECT d.id_drone FROM drone d
        INNER JOIN vehicle v
        ON d.id_drone = v.id_vehicle
        WHERE d.id_drone NOT IN (SELECT id_vehicle 
                            FROM delivery
                            WHERE delivery_end IS NULL
                            AND id_vehicle IS NOT NULL)
        AND v.id_pharmacy = (SELECT id_pharmacy
                            FROM purchaseOrder
                            WHERE id_order = idOrder);
            
    RETURN available_drones;

END;
/