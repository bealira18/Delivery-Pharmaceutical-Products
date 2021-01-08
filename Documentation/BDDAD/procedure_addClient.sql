CREATE OR REPLACE PROCEDURE addClient(email_pr VARCHAR2,
password_pr VARCHAR2,
name_pr VARCHAR2,
nif_pr NUMBER,
credit_card_pr NUMBER,
validity_date_pr DATE,
ccv_pr SMALLINT,
adr_pr VARCHAR2,
latitude_pr NUMERIC,
longitude_pr NUMERIC,
altitude_pr NUMERIC)
IS
    int_already_registered INTEGER;
BEGIN

    SELECT COUNT(*) INTO int_already_registered
    FROM registeredUser
    WHERE email = email_pr;

    IF int_already_registered != 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Email already in use');
    END IF;

    INSERT INTO registeredUser(email, password, role)
    VALUES (email_pr, password_pr, 'client');

    INSERT INTO ADDRESS(ADDRESS, LATITUDE, LONGITUDE, ALTITUDE)
    VALUES(adr_pr, latitude_pr, longitude_pr, altitude_pr);

    INSERT INTO creditcard(CREDIT_CARD, VALIDITY_DATE, CCV)
    VALUES(credit_card_pr, validity_date_pr, ccv_pr);

    INSERT INTO client(EMAIL, NAME, NIF, CREDIT_CARD, ADDRESS, CREDITS)
    VALUES(email_pr, name_pr, nif_pr, credit_card_pr, adr_pr, 0);

END;
/