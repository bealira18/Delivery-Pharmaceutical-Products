
CREATE OR REPLACE FUNCTION getNumberOfDronesInPharmacy(idPharmacy IN INTEGER)
return INTEGER
IS
    num_drones INTEGER;

begin
    SELECT count(*) into num_drones
    from DRONE INNER JOIN VEHICLE ON ID_VEHICLE = DRONE.ID_DRONE
    where ID_PHARMACY = idPharmacy;

    return (num_drones);
end;