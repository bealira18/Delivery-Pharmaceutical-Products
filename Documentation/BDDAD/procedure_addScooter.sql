CREATE OR REPLACE PROCEDURE addScooter(
    idScooter IN INTEGER, 
    idPharmacy IN INTEGER, 
    v_weight IN NUMERIC, 
    aerodynamicCoeficient IN NUMERIC, 
    frontalArea IN NUMERIC, 
    v_motor IN NUMERIC, 
    currentBattery IN NUMERIC, 
    maxBattery IN NUMERIC, 
    averageSpeed IN NUMERIC,
    scooterStatusId IN INTEGER) 
IS
BEGIN

    INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed)
    VALUES(idScooter, idPharmacy, v_weight, aerodynamicCoeficient, frontalArea, v_motor,  currentBattery, maxBattery, averageSpeed);

    INSERT INTO scooter(id_scooter, id_vehicle_status)
    VALUES(idScooter, scooterStatusId);

END;
