CREATE OR REPLACE PROCEDURE createInvoice(
    p_idOrder IN INTEGER, 
    p_idPharmacy IN INTEGER, 
    p_emailClient IN VARCHAR2,
    p_deliveryFee IN INTEGER,
    p_totalPrice IN NUMERIC,
    p_noVatPrice IN NUMERIC) 
IS
BEGIN

    INSERT INTO invoice(id_order, id_pharmacy, email_client, delivery_fee, total_price, no_VAT_price)
    VALUES (p_idOrder, p_idPharmacy, p_emailClient, p_deliveryFee, p_totalPrice, p_noVatPrice);
    
END;