CREATE OR REPLACE FUNCTION getDroneById(idDrone IN INTEGER) RETURN
SYS_REFCURSOR is matching_drone sys_refcursor;

BEGIN
    OPEN matching_drone FOR

    SELECT * FROM drone
    WHERE id_drone = idDrone;

RETURN matching_drone;
END;