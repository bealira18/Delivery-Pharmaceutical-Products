CREATE OR REPLACE FUNCTION getEmailNameFromParkedVehicleResponsible(
    idVehicle IN INTEGER)
RETURN SYS_REFCURSOR
IS
    vehicle_type VARCHAR2(255);
    matching_user SYS_REFCURSOR;
BEGIN
    
    vehicle_type := typeOfVehicleByID(idVehicle);
    
    IF (vehicle_type = 'scooter') THEN
    
        OPEN matching_user FOR
    
        SELECT email, name
        FROM courier
        WHERE email IN (SELECT email_courier
                        FROM delivery
                        WHERE id_vehicle = idVehicle
                        ORDER BY id_order DESC
                        FETCH FIRST 1 ROW ONLY);
        
        CLOSE matching_user;
    END IF;
    
    IF (vehicle_type = 'drone') THEN
    
        OPEN matching_user FOR
        
        SELECT email, name
        FROM administrator
        WHERE id_pharmacy IN (SELECT id_pharmacy 
                                FROM vehicle
                                WHERE id_vehicle = idVehicle);
        
        CLOSE matching_user;
    END IF;
    
RETURN matching_user;
END;