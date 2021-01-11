CREATE OR REPLACE FUNCTION getNextAvailableScooter(idPharmacy IN INTEGER) 
RETURN SYS_REFCURSOR IS next_scooter SYS_REFCURSOR; 

BEGIN
    OPEN next_scooter FOR
        SELECT d.id_vehicle, d.delivery_end
        FROM delivery d
        INNER JOIN scooter s
        ON d.id_vehicle = s.id_scooter
        INNER JOIN purchaseOrder p
        ON d.id_order = p.id_order
        WHERE d.delivery_end > sysdate
        AND p.id_pharmacy = idPharmacy        
        ORDER BY d.delivery_end
        FETCH FIRST 1 ROW ONLY;
    
    RETURN next_scooter;
    
END;