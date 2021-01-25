
CREATE OR REPLACE PROCEDURE updateProductStockAfterSale(idOrder in INTEGER)
IS
    auxQuantity INTEGER;
    idPharmacy INTEGER;

    CURSOR productLine IS
        SELECT * FROM PRODUCTLINE
        WHERE ID_ORDER = idOrder;
      aux productLine%ROWTYPE;

BEGIN

    SELECT ID_PHARMACY INTO idPharmacy
    FROM PURCHASEORDER
    WHERE ID_ORDER = idOrder;

    OPEN productLine;
        LOOP
            FETCH productLine INTO aux;
            EXIT WHEN productLine%NOTFOUND;

            SELECT QUANTITY INTO auxQuantity
            FROM STOCK
            WHERE ID_PHARMACY = idPharmacy AND ID_PRODUCT = aux.ID_PRODUCT;

            auxQuantity := auxQuantity - aux.PRODUCT_QUANTITY;

            UPDATE STOCK SET QUANTITY = auxQuantity
            WHERE ID_PHARMACY = idPharmacy AND ID_PRODUCT = aux.ID_PRODUCT;

        END LOOP;
    CLOSE  productLine;

END;