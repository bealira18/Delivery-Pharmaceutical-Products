CREATE OR REPLACE PROCEDURE ADDPHARMACY(
    a_name IN VARCHAR2, 
    a_lat IN NUMERIC, 
    a_lon IN NUMERIC,
    a_alt IN NUMERIC, 
    p_name IN VARCHAR2, 
    p_limit_scooter_park IN INTEGER,
    p_limit_drone_park IN INTEGER) 
IS
    v_pharmacy_id        INTEGER;
    i_does_address_exist INTEGER;
BEGIN
    
    i_does_address_exist := doesAddressExist(a_name);

    IF i_does_address_exist > 0 THEN
        addAddress(a_name, a_lat, a_lon, a_alt);
    END IF;
    
    INSERT INTO pharmacy(name, address) VALUES(p_name, a_name);

END;