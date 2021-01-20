CREATE OR REPLACE FUNCTION getProductLinesFromOrder(
    idOrder IN INTEGER)
RETURN SYS_REFCURSOR 
IS
    matching_product_lines SYS_REFCURSOR;
BEGIN

    OPEN matching_product_lines FOR
    
    SELECT id_product, product_quantity, price
    FROM productLine
    WHERE id_order = idOrder;
    
    CLOSE matching_product_lines;
    
RETURN matching_product_lines;
END;