CREATE OR REPLACE PROCEDURE nrChargingStations(nrCS scooterpark.num_charging_stations%type,idp scooterpark.id_park%type) as
BEGIN
    update scooterpark set num_charging_stations=nrCS where id_park=idp;
--COMMIT;
END;
/