CREATE OR REPLACE FUNCTION getProductsFromPharmacy(
    idPharmacy IN INTEGER)
RETURN SYS_REFCURSOR 
IS
    products SYS_REFCURSOR;
BEGIN

    OPEN products FOR
        
        SELECT DISTINCT *
        FROM productCategory pc
        INNER JOIN product p
        ON pc.id_category = p.id_category
        WHERE p.id_product IN (SELECT id_product
                                FROM stock
                                WHERE id_pharmacy = idPharmacy)
        ORDER BY pc.id_category, p.id_product;
        
    CLOSE products;
    
RETURN products;
END;