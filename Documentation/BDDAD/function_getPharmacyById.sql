CREATE OR REPLACE FUNCTION getPharmacyById(pharmacy_id IN VARCHAR) 
RETURN SYS_REFCURSOR IS matching_pharmacy SYS_REFCURSOR;
BEGIN

    OPEN matching_pharmacy FOR
        SELECT * FROM pharmacy
        WHERE id_pharmacy = pharmacy_id;
    CLOSE matching_pharmacy;
    
RETURN (matching_pharmacy);
END;
/