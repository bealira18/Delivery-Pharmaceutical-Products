CREATE OR REPLACE PROCEDURE addAdministrator(email in VARCHAR, idPharmacy in INTEGER, name in VARCHAR, nif in INTEGER, social_security in LONG) IS

BEGIN

    INSERT INTO ADMINISTRATOR(EMAIL, ID_PHARMACY, NAME, NIF, SOCIAL_SECURITY)
    VALUES(email, idPharmacy, name, nif, social_security);

END;