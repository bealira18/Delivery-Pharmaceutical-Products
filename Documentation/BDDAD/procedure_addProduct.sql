
CREATE OR REPLACE PROCEDURE addProduct(p_name IN VARCHAR, p_price IN NUMERIC, p_weight IN NUMERIC, p_idCategory IN INTEGER) IS

begin
    
    INSERT INTO product(ID_PRODUCT, NAME, PRICE, WEIGHT, ID_CATEGORY)
    VALUES(ISEQ$$_2706304.nextval, p_name, p_price, p_weight, p_idCategory);

end;
