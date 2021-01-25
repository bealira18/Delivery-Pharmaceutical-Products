CREATE OR REPLACE FUNCTION getNumberOfDronesInPharmacy(
    idPharmacy IN INTEGER)
RETURN INTEGER
IS
    num_drones INTEGER;

BEGIN
    SELECT COUNT(*) INTO num_drones
    FROM drone INNER JOIN vehicle ON id_vehicle = drone.id_drone
    WHERE id_pharmacy = idPharmacy;

    RETURN (num_drones);
END;