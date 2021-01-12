CREATE OR REPLACE PROCEDURE updateCourier(v_email in VARCHAR, idPharmacy in INTEGER, v_name in VARCHAR, v_weight in NUMERIC) IS

begin

    update courier SET id_pharmacy=idPharmacy,name=v_name,weight=v_weight
    where email=v_email;

end;
/