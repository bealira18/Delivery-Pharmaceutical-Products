CREATE OR REPLACE PROCEDURE updateNrChargingStations(idPark park.id_park%type, nrCS park.num_charging_stations%type) IS

num_CS INTEGER;
idps parkingspace.id_parking_space%type;
idp parkingspace.id_park%type;
iscs parkingspace.is_charging_station%type;

cursor lista_parkingspace_maior is
select id_parking_space,id_park,is_charging_station
from parkingspace
where id_park=idpark and is_charging_station=0;

cursor lista_parkingspace_menor is
select id_parking_space,id_park,is_charging_station
from parkingspace
where id_park=idpark and is_charging_station=1;

begin
    
    select count(*) into num_cs
    from park
    where id_park=idpark;
    
    if nrcs>num_cs then
        open lista_parkingspace_maior;
        loop
            fetch lista_parkingspace_maior INTO idps,idp,iscs;
            exit when lista_parkingspace_maior%notfound;
            update parkingspace SET is_charging_station=1;
        end loop;
    end if;

    if nrcs<num_cs then
        open lista_parkingspace_menor;
        loop
            fetch lista_parkingspace_menor INTO idps,idp,iscs;
            exit when lista_parkingspace_menor%notfound;
            update parkingspace SET is_charging_station=0;
        end loop;
    end if;
    
    update park SET num_charging_stations=nrCS;

end;
/