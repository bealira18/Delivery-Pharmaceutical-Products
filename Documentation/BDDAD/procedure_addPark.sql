CREATE OR REPLACE PROCEDURE addPark(
    pharmacy_id IN NUMBER, 
    p_limit IN NUMBER,
    p_num_charging_stations IN NUMBER, 
    p_category IN VARCHAR2,
    p_address IN VARCHAR2) 
IS
    v_park_id           NUMBER;
    i                   INTEGER;
BEGIN

    IF p_limit < p_num_charging_stations THEN
        RAISE_APPLICATION_ERROR(-20001, 'Cant have more charging stations than spaces.');
    END IF;

    INSERT INTO park(id_pharmacy, limit, num_charging_stations, category, address)
    VALUES(pharmacy_id, p_limit, p_num_charging_stations, p_category, p_address);
    
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