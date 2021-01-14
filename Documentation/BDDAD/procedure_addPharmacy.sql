CREATE OR REPLACE PROCEDURE ADDPHARMACY(
    a_name IN VARCHAR2, 
    a_lat IN NUMERIC, 
    a_lon IN NUMERIC,
    a_alt IN NUMERIC, 
    p_name IN VARCHAR2, 
    p_limit_scooter_park IN INTEGER,
    p_limit_drone_park IN INTEGER) IS

    v_pharmacy_id       NUMBER;

BEGIN

    INSERT INTO PHARMACY(name, address)
    VALUES(p_name, a_name);

    IF NOT (doesAddressExist(a_name)) THEN
        ADDADDRESS(a_name, a_lat, a_lon, a_alt);
    END IF;
    
    SELECT id_pharmacy INTO v_pharmacy_id
    FROM pharmacy
    ORDER BY id_pharmacy DESC
    FETCH FIRST 1 ROWS ONLY;
    
    -- By default, on Pharmacy creation, only 1 charging station will exist.
    addPark(v_pharmacy_id, p_limit, 1, 'scooter', a_name);
    addPark(v_pharmacy_id, p_limit, 1, 'drone', a_name);
END;
