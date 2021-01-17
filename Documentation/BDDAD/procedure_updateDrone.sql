CREATE OR REPLACE PROCEDURE updateDrone(
    idDrone IN INTEGER, 
    idPharmacy IN INTEGER, 
    v_weight IN NUMERIC, 
    frontalArea IN NUMERIC, 
    v_motor IN NUMERIC, 
    currentBattery IN NUMERIC, 
    maxBattery IN NUMERIC) 
IS
BEGIN

    UPDATE vehicle SET weight = v_weight, frontal_area = frontalArea, motor = v_motor, current_battery = currentBattery, max_battery = maxBattery
    WHERE id_vehicle = idDrone AND id_pharmacy = idPharmacy;

END;