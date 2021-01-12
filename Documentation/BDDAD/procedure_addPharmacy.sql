CREATE OR REPLACE PROCEDURE ADDPHARMACY(a_name IN VARCHAR2, a_lat IN NUMERIC, a_lon IN NUMERIC,
                                        a_alt IN NUMERIC, p_name IN VARCHAR2, p_limit IN INTEGER) IS

    v_pharmacy_id       NUMBER;

BEGIN

    v_pharmacy_id := ISEQ$$_1782023.NEXTVAL;

    INSERT INTO PHARMACY(ID_PHARMACY, NAME, ADDRESS)
    VALUES(v_pharmacy_id, p_name, a_name);

    IF NOT (DOESADDRESSEXIST(a_name)) THEN
        ADDADDRESS(a_name, a_lat, a_lon, a_alt);
    END IF;

    -- By default, on Pharmacy creation, only 1 charging station will exist.
    ADDSCOOTERPARK(v_pharmacy_id, p_limit, 1, a_name);
    --COMMIT;
END;