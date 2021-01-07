CREATE OR REPLACE PROCEDURE ADDSCOOTERPARK(pharmacy_id IN NUMBER, p_limit IN NUMBER,
                                           p_num_charging_stations IN NUMBER, p_address IN VARCHAR2) IS
    v_park_id           NUMBER;

BEGIN

    v_park_id := ISEQ$$_1706058.NEXTVAL;

    INSERT INTO SCOOTERPARK(ID_PARK, ID_PHARMACY, LIMIT, NUM_CHARGING_STATIONS, ADDRESS)
    VALUES(v_park_id, pharmacy_id, p_limit, p_num_charging_stations, p_address);

    -- By default, 1 parking space will be a charging station.
    INSERT INTO PARKINGSPACE(ID_PARKING_SPACE, ID_PARK, ID_SCOOTER, IS_CHARGING_STATION)
    VALUES(1, v_park_id, NULL, 1);

    IF p_limit > 1 THEN
        FOR ps_id IN 2..p_limit
            LOOP
                INSERT INTO PARKINGSPACE(ID_PARKING_SPACE, ID_PARK, ID_SCOOTER, IS_CHARGING_STATION)
                VALUES(ps_id, v_park_id, NULL, 0);
            END LOOP;
    END IF;
    --COMMIT;
END;