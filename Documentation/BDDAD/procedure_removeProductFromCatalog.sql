
CREATE OR REPLACE PROCEDURE removeProductFromCatalog(idPharmacy in INTEGER, idProduct in INTEGER)
IS

BEGIN
     DELETE FROM STOCK
     WHERE ID_PHARMACY = idPharmacy AND ID_PRODUCT = idProduct;

END;