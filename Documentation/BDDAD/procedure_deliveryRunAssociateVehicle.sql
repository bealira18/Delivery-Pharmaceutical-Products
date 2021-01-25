CREATE OR REPLACE PROCEDURE deliveryRunAssociateVehicle(
    p_idVehicle IN INTEGER, 
    p_deliveryRun IN INTEGER)
IS
BEGIN

    UPDATE delivery SET id_vehicle = p_idVehicle
    WHERE delivery_run = p_deliveryRun;
    
END;