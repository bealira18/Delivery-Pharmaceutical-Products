CREATE OR REPLACE PROCEDURE addProductToPharmacyCatalog(
    idPharmacy IN INTEGER, 
    idProduct in INTEGER) 
IS
BEGIN

    INSERT INTO STOCK(ID_PHARMACY, ID_PRODUCT, QUANTITY)
    VALUES(idPharmacy, idProduct, 0);

END;