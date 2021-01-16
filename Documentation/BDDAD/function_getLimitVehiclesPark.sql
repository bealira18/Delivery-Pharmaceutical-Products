CREATE OR REPLACE FUNCTION getLimitVehiclesPark(
    idPharmacy IN INTEGER, 
    vehicleType IN VARCHAR2)
RETURN INTEGER
IS
    i_limit INTEGER;
BEGIN
    SELECT SUM(limit) INTO i_limit
    FROM park
    WHERE id_pharmacy = idPharmacy AND category = vehicleType;

    return (i_limit);
END;