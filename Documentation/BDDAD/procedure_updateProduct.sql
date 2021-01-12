CREATE OR REPLACE PROCEDURE updateProduct(idProduct in INTEGER, v_name in VARCHAR, idCategory in INTEGER, v_price in NUMERIC, v_weight in NUMERIC) IS

begin

    update product SET name=v_name,id_category=idCategory,price=v_price,weight=v_weight
    where id_product=idProduct;

end;
/