
CREATE OR REPLACE FUNCTION getNumberOfScootersInPharmacy(idPharmacy IN INTEGER)
return INTEGER
IS
    num_scooters INTEGER;

begin
    SELECT count(*) into num_scooters
    from SCOOTER INNER JOIN VEHICLE ON ID_VEHICLE = ID_SCOOTER
    where ID_PHARMACY = idPharmacy;

    return (num_scooters);
end;