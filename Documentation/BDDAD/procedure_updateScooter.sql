CREATE OR REPLACE PROCEDURE updateScooter(idScooter in INTEGER, idPharmacy in INTEGER, v_weight in NUMERIC, frontalArea in NUMERIC, v_motor in NUMERIC, currentBattery in NUMERIC, maxBattery in NUMERIC) IS

begin

    update vehicle SET weight=v_weight,frontal_area=frontalArea,motor=v_motor,current_battery=currentBattery,max_battery=maxBattery
    where id_vehicle=idScooter and id_pharmacy=idPharmacy;

end;
/