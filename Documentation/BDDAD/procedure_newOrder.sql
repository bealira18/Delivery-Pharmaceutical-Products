CREATE OR REPLACE PROCEDURE newOrder(
    idPharmacy IN INTEGER, 
    email in VARCHAR) 
IS
BEGIN

    INSERT INTO purchaseorder(id_pharmacy,email_client,emission_date)
    VALUES (idPharmacy,email,SYSDATE);

END;