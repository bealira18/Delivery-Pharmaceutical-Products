
CREATE OR REPLACE FUNCTION getNumberOfScootersInPharmacy(idPharmacy IN INTEGER)
return INTEGER
IS
    num_scooters INTEGER;

begin
    SELECT count(*) into num_scooters
    from SCOOTER
    where ID_PHARMACY = idPharmacy;

    return (num_scooters);
end;