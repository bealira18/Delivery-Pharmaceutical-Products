CREATE OR REPLACE PROCEDURE addPark(
    p_pharmacy_id IN INTEGER, 
    p_limit IN INTEGER,
    p_num_charging_stations IN INTEGER, 
    p_category IN VARCHAR2,
    p_address IN VARCHAR2,
    p_maxChargingPotency IN NUMERIC) 
IS
    v_park_id NUMERIC;
    i INTEGER;
BEGIN

    IF p_limit < p_num_charging_stations THEN
        RAISE_APPLICATION_ERROR(-20001, 'Cant have more charging stations than spaces.');
    END IF;

    INSERT INTO park(id_pharmacy, limit, num_charging_stations, category, address, max_charging_potency)
    VALUES(p_pharmacy_id, p_limit, p_num_charging_stations, p_category, p_address, p_maxChargingPotency);
    
    SELECT id_park INTO v_park_id 
    FROM park
    ORDER BY id_park DESC
    FETCH FIRST 1 ROWS ONLY;
    
    FOR i IN 1..p_limit LOOP
        IF i > p_num_charging_stations THEN
            INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) 
                VALUES (i, v_park_id, NULL, 0);
        ELSE 
            INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) 
                VALUES (i, v_park_id, NULL, 1);
        END IF;
    END LOOP;

END;