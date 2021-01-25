
CREATE OR REPLACE PROCEDURE addScooter(idScooter in INTEGER, idPharmacy in INTEGER, v_weight in NUMERIC, aerodynamicCoeficient in NUMERIC, frontalArea in NUMERIC, v_motor in NUMERIC, currentBattery in NUMERIC, maxBattery in NUMERIC, scooterStatusId in INTEGER) IS

begin

    INSERT INTO VEHICLE(ID_VEHICLE, ID_PHARMACY, WEIGHT, AERODYNAMIC_COEFFICIENT, FRONTAL_AREA, MOTOR, CURRENT_BATTERY, MAX_BATTERY)
    VALUES(idScooter, idPharmacy, v_weight, aerodynamicCoeficient, frontalArea, v_motor,  currentBattery, maxBattery);

    INSERT INTO scooter(ID_SCOOTER, ID_SCOOTER_STATUS)
    VALUES(idScooter, scooterStatusId);

end;
