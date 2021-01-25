CREATE OR REPLACE FUNCTION checkIfProductExistsInCatalog(
    idPharmacy IN INTEGER, 
    idProduct IN INTEGER)
RETURN INTEGER
IS
    is_same_product  INTEGER;
BEGIN
    SELECT COUNT(*) INTO is_same_product
    FROM stock
    WHERE id_pharmacy = idPharmacy AND id_product = idProduct;

    RETURN is_same_product;
END;