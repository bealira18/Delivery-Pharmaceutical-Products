CREATE OR REPLACE FUNCTION getClientEmailFromOrder(idOrder INTEGER)
RETURN client.email%TYPE
IS
    clientEmail client.email%TYPE;

BEGIN

    SELECT EMAIL_CLIENT INTO clientEmail
    FROM PURCHASEORDER
    WHERE ID_ORDER = idOrder;

    RETURN clientEmail;
END;