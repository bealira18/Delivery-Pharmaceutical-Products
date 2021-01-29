CREATE OR REPLACE PROCEDURE updateVehicleStatus(
    p_idVehicle IN INTEGER,
    p_status IN VARCHAR2)
IS
    int_id_vehicle_status INTEGER;
    vehicle_type VARCHAR2(255);
BEGIN
    SELECT id_vehicle_status INTO int_id_vehicle_status
    FROM vehicleStatus
    WHERE name = p_status;

    vehicle_type := typeOfVehicleByID(p_idVehicle);
    
    IF (vehicle_type = 'scooter') THEN
    
        UPDATE scooter SET id_vehicle_status = int_id_vehicle_status
        WHERE id_scooter = p_idVehicle;
    END IF;
    
    IF (vehicle_type = 'drone') THEN
    
        UPDATE drone SET id_vehicle_status = int_id_vehicle_status
        WHERE id_drone = p_idVehicle;
    END IF;

END;