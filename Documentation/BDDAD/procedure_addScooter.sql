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
    scooterStatusId IN INTEGER,
    qr_code IN BLOB) 
IS
BEGIN

    INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode)
    VALUES(idScooter, idPharmacy, v_weight, aerodynamicCoeficient, frontalArea, v_motor,  currentBattery, maxBattery, averageSpeed, qr_code);

    INSERT INTO scooter(id_scooter, id_vehicle_status)
    VALUES(idScooter, scooterStatusId);

END;