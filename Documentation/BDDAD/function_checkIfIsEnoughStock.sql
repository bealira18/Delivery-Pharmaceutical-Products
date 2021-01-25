CREATE OR REPLACE FUNCTION checkIfIsEnoughStock(idPharmacy IN INTEGER, idProduct IN INTEGER, productQuantity IN INTEGER)
RETURN INTEGER
IS
    maxQuantity INTEGER;
    auxQ INTEGER;
    hasStock INTEGER;

BEGIN

    hasStock := 0;

    SELECT QUANTITY INTO maxQuantity
    FROM STOCK
    WHERE ID_PHARMACY = idPharmacy AND ID_PRODUCT = idProduct;

    auxQ := productQuantity - maxQuantity;

    IF auxQ > 0 THEN
     hasStock := auxQ;
    END IF;

    RETURN hasStock;
END;