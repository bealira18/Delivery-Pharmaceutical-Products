CREATE OR REPLACE FUNCTION getPharmacyById(pharmacy_id IN INTEGER) 
RETURN SYS_REFCURSOR IS matching_pharmacy SYS_REFCURSOR;
BEGIN

    OPEN matching_pharmacy FOR
        SELECT * FROM pharmacy
        WHERE id_pharmacy = pharmacy_id;
    
RETURN (matching_pharmacy);
END;
