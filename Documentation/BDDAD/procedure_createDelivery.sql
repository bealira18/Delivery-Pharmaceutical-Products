CREATE OR REPLACE PROCEDURE createDelivery(
    id_order IN INTEGER, 
    delivery_run IN INTEGER) 
IS
    delivery_status INTEGER;
BEGIN

    SELECT id_delivery_status INTO delivery_status
    FROM deliveryStatus
    WHERE name = 'processing';
    
    INSERT INTO delivery (id_order, id_delivery_status, delivery_run)
    VALUES (id_order, delivery_status, delivery_run);
    
END;