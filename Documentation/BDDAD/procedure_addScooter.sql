
CREATE OR REPLACE PROCEDURE addScooter(s_idPharmacy in INTEGER, s_idScooterStatus in INTEGER, s_currentBattery in NUMERIC, s_maxBattery in NUMERIC) IS

begin
    
    INSERT INTO scooter(ID_SCOOTER, ID_PHARMACY, ID_SCOOTER_STATUS, CURRENT_BATTERY, MAX_BATTERY)
    VALUES(ISEQ$$_1723670.nextval, s_idPharmacy, s_idScooterStatus, s_currentBattery, s_maxBattery);

end;
