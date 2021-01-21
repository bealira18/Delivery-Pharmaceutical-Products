CREATE OR REPLACE PROCEDURE ADDPATH(p_address1 IN VARCHAR2, p_address2 IN VARCHAR2,
                                    p_kcoef IN NUMERIC, p_wind_angle IN NUMERIC, p_wind_speed IN NUMERIC) IS

BEGIN
    INSERT INTO PATH(ADDRESS1, ADDRESS2, KINETIC_COEFFICIENT, WIND_ANGLE, WIND_SPEED)
    VALUES(p_address1, p_address2, p_kcoef, p_wind_angle, p_wind_speed);
END;