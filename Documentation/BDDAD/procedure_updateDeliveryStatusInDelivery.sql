CREATE OR REPLACE PROCEDURE updateDeliveryStatusInDelivery(idOrder in INTEGER)
IS
    idDeliveryStatus INTEGER;
    name VARCHAR2(255);

BEGIN

    name := 'in delivery';

    SELECT ID_DELIVERY_STATUS INTO idDeliveryStatus
    FROM DELIVERY
    WHERE ID_ORDER = idOrder;

    UPDATE DELIVERYSTATUS SET NAME = name
    WHERE ID_DELIVERY_STATUS = idDeliveryStatus;

END;