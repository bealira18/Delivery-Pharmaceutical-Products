CREATE OR REPLACE FUNCTION getClientByEmail(
    emailClient IN VARCHAR) 
RETURN SYS_REFCURSOR 
IS 
    matching_client SYS_REFCURSOR;
BEGIN
    OPEN matching_client FOR

    SELECT * FROM client
    WHERE email = emailClient;

RETURN matching_client;
END;
