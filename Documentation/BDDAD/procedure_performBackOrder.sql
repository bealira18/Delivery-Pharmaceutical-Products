CREATE OR REPLACE PROCEDURE performBackOrder(
    p_idPharmacy1 IN INTEGER, 
    p_idPharmacy2 IN INTEGER, 
    p_idProduct IN INTEGER, 
    p_productQuantity IN INTEGER)
IS
BEGIN

    INSERT INTO backOrder(idPharmacy1, idPharmacy2, idProduct, quantity)
    VALUES (p_idPharmacy1, p_idPharmacy2, p_idProduct, p_productQuantity);

    UPDATE stock SET quantity = quantity + p_productQuantity
    WHERE id_pharmacy = p_idPharmacy1 AND id_product = p_idProduct;

    UPDATE stock SET quantity = quantity - p_productQuantity
    WHERE id_pharmacy = p_idPharmacy2 AND id_product = p_idProduct;

END;