CREATE OR REPLACE PROCEDURE updateProduct(
    idProduct IN INTEGER, 
    v_name IN VARCHAR, 
    idCategory IN INTEGER, 
    v_price IN NUMERIC, 
    v_weight IN NUMERIC) 
IS
BEGIN

    UPDATE product SET name = v_name, id_category = idCategory, price = v_price, weight = v_weight
    WHERE id_product = idProduct;

END;