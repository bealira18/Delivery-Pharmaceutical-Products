CREATE OR REPLACE FUNCTION getHighestBatteryDrone(
    idPharmacy IN INTEGER) 
RETURN SYS_REFCURSOR 
IS 
    drone_highest SYS_REFCURSOR;
    available_id INTEGER;
BEGIN

    SELECT id_vehicle_status INTO available_id
    FROM vehicleStatus
    WHERE name = 'available';

    OPEN drone_highest FOR
    
    SELECT * FROM vehicle
    WHERE id_vehicle IN ( SELECT id_drone FROM drone
                            WHERE id_vehicle_status = available_id)
    AND id_pharmacy = idPharmacy
    ORDER BY current_battery DESC
    FETCH FIRST 1 ROW ONLY;
    
    RETURN drone_highest;
END;