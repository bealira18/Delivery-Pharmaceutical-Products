CREATE OR REPLACE PROCEDURE addDelivery(
    id_order IN INTEGER, 
    id_vehicle IN INTEGER,
    email_courier IN VARCHAR2,
    id_delivery_status IN INTEGER,
    delivery_start IN DATE,
    delivery_end IN DATE,
    delivery_run IN INTEGER) 
IS    
BEGIN
    
    INSERT INTO delivery (id_order, id_vehicle, email_courier, id_delivery_status, delivery_start, delivery_end, delivery_run)
    VALUES (id_order, id_vehicle, email_courier, id_delivery_status, delivery_start, delivery_end, delivery_run);
    
END;