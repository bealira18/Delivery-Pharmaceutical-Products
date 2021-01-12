CREATE OR REPLACE PROCEDURE ADDADDRESS(a_name IN VARCHAR2, a_lat IN NUMERIC,
                                       a_lon IN NUMERIC, a_alt IN NUMERIC) IS

BEGIN
    INSERT INTO ADDRESS(ADDRESS, LATITUDE, LONGITUDE, ALTITUDE)
    VALUES(a_name, a_lat, a_lon, a_alt);
    --COMMIT;
END;