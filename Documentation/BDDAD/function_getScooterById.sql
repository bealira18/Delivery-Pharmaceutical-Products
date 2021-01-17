CREATE OR REPLACE FUNCTION getScooterById(
    idScooter IN INTEGER) 
RETURN SYS_REFCURSOR 
IS 
    matching_scooter SYS_REFCURSOR;
BEGIN
    OPEN matching_scooter FOR

    SELECT * FROM scooter
    WHERE id_scooter = idScooter;

RETURN matching_scooter;
END;