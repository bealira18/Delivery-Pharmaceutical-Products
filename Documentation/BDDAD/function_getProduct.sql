create or replace FUNCTION getProduct(idp INT) RETURN
SYS_REFCURSOR is resultado sys_refcursor;

BEGIN
    OPEN resultado FOR

    SELECT * FROM product
    WHERE product.id_product = idp;

RETURN (resultado);
END;
/
