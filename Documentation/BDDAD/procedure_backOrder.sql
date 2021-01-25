CREATE OR REPLACE PROCEDURE backOrder(idPharmacy1 IN INTEGER, idPharmacy2 IN INTEGER, idProduct IN INTEGER, productQuantity IN INTEGER)
IS

BEGIN

    INSERT INTO BACKORDER(IDPHARMACY1, IDPHARMACY2, IDPRODUCT, QUANTITY)
    VALUES (idPharmacy1, idPharmacy2, idProduct, productQuantity);

    UPDATE STOCK SET QUANTITY = QUANTITY + productQuantity
    WHERE ID_PHARMACY = idPharmacy1 AND ID_PRODUCT = idProduct;

    UPDATE STOCK SET QUANTITY = QUANTITY - productQuantity
    WHERE ID_PHARMACY = idPharmacy2 AND ID_PRODUCT = idProduct;

END;