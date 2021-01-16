CREATE OR REPLACE PROCEDURE updateCourier(
    v_email IN VARCHAR, 
    idPharmacy IN INTEGER, 
    v_name IN VARCHAR, 
    v_weight IN NUMERIC) 
IS
BEGIN
    UPDATE courier SET id_pharmacy = idPharmacy, name = v_name, weight = v_weight
    WHERE email = v_email;
END;
