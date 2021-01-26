CREATE OR REPLACE FUNCTION getPharmacyByAddress(
    p_pharmacyAddress IN VARCHAR2) 
RETURN SYS_REFCURSOR 
IS 
    matching_pharmacy SYS_REFCURSOR;
BEGIN

    OPEN matching_pharmacy FOR
        SELECT * FROM pharmacy
        WHERE address = p_pharmacyAddress;
    
RETURN matching_pharmacy;
END;