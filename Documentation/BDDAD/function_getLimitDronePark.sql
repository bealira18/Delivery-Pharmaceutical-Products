
CREATE OR REPLACE FUNCTION getLimitDronePark(idPharmacy IN INTEGER)
return INTEGER
IS
    i_limit INTEGER;

begin
    SELECT SUM(LIMIT) INTO i_limit
    from PARK
    where ID_PHARMACY = idPharmacy AND CATEGORY = 'drone';

    return (i_limit);
end;