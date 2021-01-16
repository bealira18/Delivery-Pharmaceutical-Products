CREATE OR REPLACE PROCEDURE updatePharmacy(
    idPharmacy IN INTEGER, 
    v_name IN VARCHAR) 
IS
BEGIN

    UPDATE pharmacy SET name = v_name
    WHERE id_pharmacy = idPharmacy;

END;