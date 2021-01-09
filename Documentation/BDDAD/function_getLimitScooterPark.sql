
CREATE OR REPLACE FUNCTION getLimitScooterPark(idPharmacy IN INTEGER)
return INTEGER
IS
    i_limit INTEGER;

begin
    SELECT LIMIT INTO i_limit
    from SCOOTERPARK
    where ID_PHARMACY = idPharmacy;

    return (i_limit);
end;
