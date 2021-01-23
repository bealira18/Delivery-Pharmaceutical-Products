CREATE OR REPLACE FUNCTION checkIfIsEnoughStockInOtherPharmacy(idOrder IN INTEGER)
RETURN BOOLEAN
IS
    auxQuantity INTEGER;
    auxQ INTEGER;
    hasStock INTEGER;

    CURSOR productLine IS
        SELECT * FROM PRODUCTLINE
        WHERE ID_ORDER = idOrder;
     aux productLine%ROWTYPE;

BEGIN

    hasStock := 0;

    OPEN productLine;
        LOOP
            FETCH productLine INTO aux;
            EXIT WHEN productLine%NOTFOUND;

            SELECT QUANTITY INTO auxQuantity
            FROM STOCK
            WHERE ID_PRODUCT = aux.ID_PRODUCT;

            auxQ := auxQuantity - aux.PRODUCT_QUANTITY;

            IF auxQ < 0 THEN
                hasStock := 1;
            END IF;

        END LOOP;
    CLOSE productLine;

    RETURN hasStock = 0;
END;