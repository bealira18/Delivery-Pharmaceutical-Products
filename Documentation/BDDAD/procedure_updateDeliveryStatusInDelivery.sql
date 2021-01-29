CREATE OR REPLACE PROCEDURE updateDeliveryStatusInDelivery(
    p_idOrder in INTEGER)
IS
    idDeliveryStatus INTEGER;
    str_name VARCHAR2(255);
    int_id_vehicle INTEGER;

BEGIN

    str_name := 'in delivery';

    SELECT id_delivery_status INTO idDeliveryStatus
    FROM deliveryStatus
    WHERE name = str_name;

    UPDATE delivery SET id_delivery_status = idDeliveryStatus, delivery_start = sysdate
    WHERE id_order = p_idOrder;
    
    SELECT id_vehicle INTO int_id_vehicle
    FROM delivery
    WHERE id_order = p_idOrder;
    
    IF int_id_vehicle IS NOT NULl THEN
        updateVehicleStatus(int_id_vehicle, 'occupied');
    END IF;

END;