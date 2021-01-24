CREATE OR REPLACE FUNCTION getProductLinesFromOrder(
    idOrder IN INTEGER)
RETURN SYS_REFCURSOR 
IS
    matching_product_lines SYS_REFCURSOR;
BEGIN

    OPEN matching_product_lines FOR
    
    SELECT *
    FROM productLine
    WHERE id_order = idOrder;
        
RETURN matching_product_lines;
END;