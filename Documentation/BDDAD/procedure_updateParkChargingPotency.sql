CREATE OR REPLACE PROCEDURE updateParkChargingPotency(
    p_idPark IN INTEGER,
    p_maxChargingPotency IN NUMERIC)
IS
BEGIN
    
    UPDATE park SET max_charging_potency = p_maxChargingPotency
    WHERE id_park = p_idPark;
    
END;