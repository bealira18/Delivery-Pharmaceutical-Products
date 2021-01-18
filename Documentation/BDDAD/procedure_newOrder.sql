CREATE OR REPLACE PROCEDURE newOrder(
    idOrder IN INTEGER, 
    idPharmacy IN INTEGER, 
    email in VARCHAR) 
IS
BEGIN

    INSERT INTO purchaseorder(id_order,id_pharmacy,email_client,emission_date)
    VALUES (idOrder,idPharmacy,email,SYSDATE);

END;
/