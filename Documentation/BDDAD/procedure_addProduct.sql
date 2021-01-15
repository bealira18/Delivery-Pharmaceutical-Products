CREATE OR REPLACE PROCEDURE addProduct(
    p_name IN VARCHAR, 
    p_price IN NUMERIC, 
    p_weight IN NUMERIC, 
    p_idCategory IN INTEGER) 
IS
BEGIN
    
    INSERT INTO product(name, price, weight, id_category)
    VALUES(p_name, p_price, p_weight, p_idCategory);

end;
