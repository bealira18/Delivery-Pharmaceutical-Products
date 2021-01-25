CREATE OR REPLACE PROCEDURE addDrone(
    idDrone IN INTEGER, 
    idPharmacy IN INTEGER, 
    v_weight IN NUMERIC, 
    aerodynamicCoeficient IN NUMERIC, 
    frontalArea IN NUMERIC, 
    v_motor IN NUMERIC, 
    currentBattery IN NUMERIC, 
    maxBattery IN NUMERIC,
    averageSpeed IN NUMERIC,
    width IN NUMERIC,
    averageVerticalSpeed IN NUMERIC,
    droneStatusId IN INTEGER) 
IS
BEGIN

    INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed)
    VALUES(idDrone, idPharmacy, v_weight, aerodynamicCoeficient, frontalArea, v_motor, currentBattery, maxBattery, averageSpeed);

    INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status)
    VALUES(idDrone, width, averageVerticalSpeed, droneStatusId);

END;