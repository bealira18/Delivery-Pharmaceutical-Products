CREATE OR REPLACE PROCEDURE updateDeliveryStatusInDelivery(idOrder in INTEGER)
IS
    idDeliveryStatus INTEGER;

BEGIN
    SELECT ID_DELIVERY_STATUS INTO idDeliveryStatus
    FROM DELIVERYSTATUS
    WHERE NAME = 'in delivery';

    UPDATE DELIVERY SET ID_DELIVERY_STATUS = idDeliveryStatus
    WHERE ID_ORDER = idOrder;

END;