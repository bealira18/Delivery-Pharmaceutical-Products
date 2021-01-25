CREATE OR REPLACE PROCEDURE deliveryRunAssociateCourier(
    p_emailCourier IN VARCHAR2, 
    p_deliveryRun IN INTEGER)
IS
BEGIN

    UPDATE delivery SET email_courier = p_emailCourier
    WHERE delivery_run = p_deliveryRun;
    
END;