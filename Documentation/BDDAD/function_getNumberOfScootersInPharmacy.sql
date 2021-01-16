CREATE OR REPLACE FUNCTION getNumberOfScootersInPharmacy(idPharmacy IN INTEGER)
RETURN INTEGER
IS
    num_scooters INTEGER;

BEGIN
    SELECT COUNT(*) INTO num_scooters
    FROM scooter INNER JOIN vehicle ON id_vehicle = id_scooter
    WHERE id_pharmacy = idPharmacy;

    RETURN (num_scooters);
END;