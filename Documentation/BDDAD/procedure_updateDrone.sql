CREATE OR REPLACE PROCEDURE updateDrone(
    p_idDrone IN INTEGER, 
    p_width IN NUMERIC,
    p_averageVerticalSpeed IN NUMERIC,
    p_idVehicleStatus IN INTEGER,
    p_idPharmacy IN INTEGER, 
    p_weight IN NUMERIC, 
    p_aerodynamicCoefficient IN NUMERIC,
    p_frontalArea IN NUMERIC, 
    p_motor IN NUMERIC, 
    p_currentBattery IN NUMERIC, 
    p_maxBattery IN NUMERIC,
    p_averageSpeed IN NUMERIC) 
IS
BEGIN

    UPDATE drone SET width = p_width, average_vertical_speed = p_averageVerticalSpeed, id_vehicle_status = p_idVehicleStatus
    WHERE id_drone = p_idDrone;

    UPDATE vehicle SET id_pharmacy = p_idPharmacy, weight = p_weight, aerodynamic_coefficient = p_aerodynamicCoefficient,
            frontal_area = p_frontalArea, motor = p_motor, current_battery = p_currentBattery, max_battery = p_maxBattery, average_speed = p_averageSpeed
    WHERE id_vehicle = p_idDrone;

END;