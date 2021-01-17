CREATE OR REPLACE PROCEDURE updateNrChargingStations(
    idPark park.id_park%type, 
    nrCS park.num_charging_stations%type) 
IS
    num_CS INTEGER;
    reps INTEGER;
    idps parkingspace.id_parking_space%type;
    i INTEGER;
BEGIN
    
    SELECT num_charging_stations INTO num_cs
    FROM park
    WHERE id_park = idpark;
    
    IF nrcs > num_cs THEN
    
        reps := nrcs-num_cs;
        FOR i IN 1 .. reps LOOP
        
            SELECT id_parking_space INTO idps
            FROM parkingSpace
            WHERE id_park = idPark AND is_charging_station = 0
            ORDER BY id_parking_space DESC
            FETCH FIRST 1 ROWS ONLY;
            
            UPDATE parkingSpace SET is_charging_station = 1
            WHERE id_parking_space = idps AND id_park = idPark;
       
        END LOOP;
        
    END IF;
    
    IF nrcs < num_cs THEN
    
        reps := num_cs-nrcs;
        FOR i IN 1 .. reps LOOP
        
            SELECT id_parking_space INTO idps
            FROM parkingSpace
            WHERE id_park = idPark AND is_charging_station = 1
            ORDER BY id_parking_space ASC
            FETCH FIRST 1 ROWS ONLY;
            
            UPDATE parkingSpace SET is_charging_station = 0
            WHERE id_parking_space = idps AND id_park = idPark;
       
        END LOOP;
        
    END IF;

    
    UPDATE park SET num_charging_stations=nrCS
    WHERE id_park = idPark;

END;