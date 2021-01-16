CREATE OR REPLACE FUNCTION getProduct(idp IN INTEGER) RETURN
SYS_REFCURSOR IS resultado sys_refcursor;

BEGIN
    OPEN resultado FOR

    SELECT * FROM product
    WHERE product.id_product = idp;

RETURN (resultado);
END;