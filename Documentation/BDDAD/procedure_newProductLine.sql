CREATE OR REPLACE PROCEDURE newProductLine(
    idOrder IN INTEGER, 
    idProduct IN INTEGER, 
    quantity in INTEGER,
    v_price in NUMERIC) 
IS
BEGIN

    INSERT INTO productline(id_order,id_product,product_quantity,price)
    VALUES (idOrder,idProduct,quantity,v_price);

END;
/