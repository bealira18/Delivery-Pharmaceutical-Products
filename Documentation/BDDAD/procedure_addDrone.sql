
CREATE OR REPLACE PROCEDURE addDrone(idDrone in INTEGER, idPharmacy in INTEGER, v_weight in NUMERIC, aerodynamicCoeficient in NUMERIC, frontalArea in NUMERIC, v_motor in NUMERIC, currentBattery in NUMERIC, maxBattery in NUMERIC, droneStatusId in INTEGER) IS

begin

    INSERT INTO VEHICLE(ID_VEHICLE, ID_PHARMACY, WEIGHT, AERODYNAMIC_COEFFICIENT, FRONTAL_AREA, MOTOR, CURRENT_BATTERY, MAX_BATTERY)
    VALUES(idDrone, idPharmacy, v_weight, aerodynamicCoeficient, frontalArea, v_motor,  currentBattery, maxBattery);

    INSERT INTO DRONE(ID_DRONE, ID_VEHICLE_STATUS)
    VALUES(idDrone, droneStatusId);

end;