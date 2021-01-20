CREATE OR REPLACE FUNCTION typeOfVehicleByID(
    vehicleID_pr IN INTEGER)
    return VARCHAR2
IS
    i_vehicle_exists INTEGER :=0;

BEGIN
    SELECT COUNT(*) INTO i_vehicle_exists FROM VEHICLE
    WHERE ID_VEHICLE = vehicleID_pr;

    IF i_vehicle_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Vehicle not found');
    END IF;

    SELECT COUNT(*) INTO i_vehicle_exists FROM SCOOTER
    WHERE ID_SCOOTER = vehicleID_PR;

    IF i_vehicle_exists > 0 THEN
        return ('scooter');
    END IF;

    SELECT COUNT(*) INTO i_vehicle_exists FROM DRONE
    WHERE ID_DRONE = vehicleID_PR;

    IF i_vehicle_exists > 0 THEN
        return ('drone');
    END IF;

    RAISE_APPLICATION_ERROR(-20001, 'Vehicle is not categorized');
    return (NULL);
END;

