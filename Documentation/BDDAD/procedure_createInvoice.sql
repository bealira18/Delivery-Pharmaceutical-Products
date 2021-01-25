CREATE OR REPLACE PROCEDURE createInvoice(
    idOrder IN INTEGER, 
    idPharmacy IN INTEGER, 
    emailClient IN VARCHAR2,
    deliveryFee IN INTEGER,
    totalPrice IN NUMERIC) 
IS
BEGIN

    INSERT INTO invoice(id_order, id_pharmacy, email_client, delivery_fee, total_price)
    VALUES (idOrder, idPharmacy, emailClient, deliveryFee, totalPrice);
    
END;