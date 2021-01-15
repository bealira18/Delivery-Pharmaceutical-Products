CREATE OR REPLACE PROCEDURE addClient(
    email_pr VARCHAR2,
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
    int_already_registered INTEGER :=0;
    i_does_address_exist INTEGER :=0;
    i_does_credit_card_exist INTEGER :=0;
BEGIN

    SELECT COUNT(*) INTO int_already_registered
    FROM registeredUser
    WHERE email = email_pr;

    IF int_already_registered > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Email already in use');
    END IF;

    INSERT INTO registeredUser(email, password, role)
    VALUES (email_pr, password_pr, 'client');

    i_does_address_exist := doesAddressExist(adr_pr);

    IF i_does_address_exist > 0 THEN
        addAddress(adr_pr, latitude_pr, longitude_pr, altitude_pr);
    END IF;

    SELECT COUNT(*) INTO i_does_credit_card_exist
    FROM creditCard
    WHERE credit_card = credit_card_pr;
    
    IF i_does_credit_card_exist = 0 THEN
        INSERT INTO creditcard(credit_card, validity_date, ccv)
        VALUES(credit_card_pr, validity_date_pr, ccv_pr);
    END IF;

    INSERT INTO client(email, name, nif, credit_card, address, credits)
    VALUES(email_pr, name_pr, nif_pr, credit_card_pr, adr_pr, 0);

END;