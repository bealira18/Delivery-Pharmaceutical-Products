CREATE OR REPLACE FUNCTION getProductsFromOrder(
    order_identifier IN PURCHASEORDER.ID_ORDER%TYPE)
RETURN SYS_REFCURSOR 
IS
    result      SYS_REFCURSOR;
BEGIN

    OPEN result FOR

        SELECT * FROM product
        INNER JOIN productLine ON product.id_product = productLine.id_product
        INNER JOIN purchaseOrder ON productLine.id_order = order_identifier;

    RETURN result;
END;