CREATE OR REPLACE FUNCTION getScooterById(idScooter IN INTEGER) RETURN
SYS_REFCURSOR is matching_scooter sys_refcursor;

BEGIN
    OPEN matching_scooter FOR

    SELECT * FROM scooter
    WHERE id_scooter = idScooter;

RETURN matching_scooter;
END;