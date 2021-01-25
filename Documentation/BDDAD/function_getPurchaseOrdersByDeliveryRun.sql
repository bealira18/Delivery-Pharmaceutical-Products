CREATE OR REPLACE FUNCTION getPurchaseOrdersByDeliveryRun(
    idDeliveryRun IN INTEGER)
RETURN SYS_REFCURSOR
IS
    purchase_orders SYS_REFCURSOR;
BEGIN

    OPEN purchase_orders FOR
        SELECT * FROM purchaseOrder
        WHERE id_order IN ( SELECT id_order FROM delivery
                            WHERE delivery_run = idDeliveryRun);
                            
    RETURN purchase_orders;

END;