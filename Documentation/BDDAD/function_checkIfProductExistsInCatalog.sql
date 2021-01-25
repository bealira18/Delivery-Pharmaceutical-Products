
CREATE OR REPLACE FUNCTION checkIfProductExistsInCatalog(idPharmacy IN INTEGER, idProduct IN INTEGER)
RETURN BOOLEAN
IS
    isSameProduct  INTEGER;

BEGIN
    SELECT count(*) INTO isSameProduct
    FROM STOCk
    WHERE ID_PHARMACY = idPharmacy AND ID_PRODUCT = idProduct;

  RETURN isSameProduct > 0;
END;