CREATE OR REPLACE PROCEDURE UPDATEPATH(p_address1 IN VARCHAR2, p_address2 IN VARCHAR2,
                                    p_kcoef IN NUMERIC, p_wind_angle IN NUMERIC, p_wind_speed IN NUMERIC) IS

BEGIN
    UPDATE PATH
    SET ADDRESS1 = p_address1,
    ADDRESS2 = p_address2,
    KINETIC_COEFFICIENT = p_kcoef,
    WIND_ANGLE = p_wind_angle,
    WIND_SPEED = p_wind_speed
    WHERE ADDRESS1 = p_address1 AND ADDRESS2 = p_address2;
END;