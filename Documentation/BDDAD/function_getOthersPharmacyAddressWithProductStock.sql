CREATE OR REPLACE FUNCTION getOthersPharmacyAddressWithProductStock(
    idPharmacy IN INTEGER, 
    idProduct IN INTEGER, 
    productQuantity IN INTEGER)
RETURN SYS_REFCURSOR
IS
    matching_address SYS_REFCURSOR;

BEGIN
    OPEN matching_address FOR

        SELECT * FROM ADDRESS
        WHERE address IN (SELECT address FROM pharmacy
                          WHERE id_pharmacy IN (SELECT id_pharmacy FROM stock
                                                WHERE quantity >= productQuantity
                                                AND id_product = idProduct
                                                AND id_pharmacy != idPharmacy)
        );

    RETURN  matching_address;
END;