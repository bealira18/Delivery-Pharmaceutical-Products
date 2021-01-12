CREATE OR REPLACE PROCEDURE updatePharmacy(idPharmacy in INTEGER, v_name in VARCHAR) IS

begin

    update pharmacy SET name=v_name
    where id_pharmacy=idPharmacy;

end;
/