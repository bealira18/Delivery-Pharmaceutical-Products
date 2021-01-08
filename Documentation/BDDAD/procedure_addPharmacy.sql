CREATE OR REPLACE PROCEDURE ADDPHARMACY(p_name IN VARCHAR2, p_address IN VARCHAR2, p_limit IN NUMBER) IS

    v_pharmacy_id       NUMBER;

BEGIN

    v_pharmacy_id := ISEQ$$_1706042.NEXTVAL;

    INSERT INTO PHARMACY(ID_PHARMACY, NAME, ADDRESS)
    VALUES(v_pharmacy_id, p_name, p_address);

    -- By default, on Pharmacy creation, only 1 charging station will exist.
    ADDSCOOTERPARK(v_pharmacy_id, p_limit, 1, p_address);
    --COMMIT;
END;