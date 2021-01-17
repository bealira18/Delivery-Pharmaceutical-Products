CREATE OR REPLACE PROCEDURE addAddress(
    a_name IN VARCHAR2, 
    a_lat IN NUMERIC,
    a_lon IN NUMERIC, 
    a_alt IN NUMERIC) 
IS
BEGIN
    INSERT INTO address(address, latitude, longitude, altitude)
    VALUES(a_name, a_lat, a_lon, a_alt);
END;