CREATE OR REPLACE PROCEDURE removeProductFromCatalog(idPharmacy IN INTEGER, idProduct IN INTEGER)
IS
BEGIN
     DELETE FROM stock
     WHERE id_pharmacy = idPharmacy AND id_product = idProduct;

END;