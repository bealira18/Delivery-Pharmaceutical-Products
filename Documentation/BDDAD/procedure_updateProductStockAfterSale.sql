CREATE OR REPLACE PROCEDURE updateProductStockAfterSale(idOrder in INTEGER)
IS
    auxQuantity INTEGER;
    idPharmacy INTEGER;

    CURSOR productLine IS
        SELECT * FROM PRODUCTLINE
        WHERE id_order = idOrder;
      aux productLine%ROWTYPE;

BEGIN

    SELECT id_pharmacy INTO idPharmacy
    FROM purchaseOrder
    WHERE id_order = idOrder;

    OPEN productLine;
        LOOP
            FETCH productLine INTO aux;
            EXIT WHEN productLine%NOTFOUND;

            SELECT quantity INTO auxQuantity
            FROM stock
            WHERE id_pharmacy = idPharmacy AND id_product = aux.id_product;

            auxQuantity := auxQuantity - aux.product_quantity;

            UPDATE stock SET quantity = auxQuantity
            WHERE id_pharmacy = idPharmacy AND id_product = aux.ID_PRODUCT;

        END LOOP;
    CLOSE  productLine;

END;