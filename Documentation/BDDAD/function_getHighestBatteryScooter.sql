CREATE OR REPLACE FUNCTION getHighestBatteryScooter(
    idPharmacy IN INTEGER) 
RETURN SYS_REFCURSOR 
IS 
    scooter_highest SYS_REFCURSOR;
    available_id INTEGER;
BEGIN

    SELECT id_vehicle_status INTO available_id
    FROM vehicleStatus
    WHERE name = 'available';

    OPEN scooter_highest FOR
    
    SELECT * FROM vehicle
    WHERE id_vehicle IN ( SELECT id_scooter FROM scooter
                            WHERE id_vehicle_status = available_id)
    AND id_pharmacy = idPharmacy
    ORDER BY current_battery DESC
    FETCH FIRST 1 ROW ONLY;
    
    RETURN scooter_highest;
END;