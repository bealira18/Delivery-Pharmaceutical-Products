CREATE OR REPLACE FUNCTION getDroneById(
    idDrone IN INTEGER) 
RETURN SYS_REFCURSOR 
IS 
    matching_drone SYS_REFCURSOR;

BEGIN
    OPEN matching_drone FOR

    SELECT * FROM drone
    WHERE id_drone = idDrone;

RETURN matching_drone;
END;