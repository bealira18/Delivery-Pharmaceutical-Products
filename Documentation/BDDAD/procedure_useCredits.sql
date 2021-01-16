CREATE OR REPLACE PROCEDURE useCredits(
    email_client IN client.email%type, 
    idInvoice IN invoice.id_invoice%type) 
IS
    c INTEGER;
    d INTEGER;
    total INTEGER;
BEGIN

    SELECT credits INTO c
    FROM client
    WHERE email=email_client;
    
    SELECT delivery_fee INTO d
    FROM invoice
    WHERE id_invoice=idInvoice;
    
    IF c>=d THEN
        total:=c-d;
    
        UPDATE client SET credits=total
        WHERE email=email_client;
    
        UPDATE invoice SET delivery_fee=0
        WHERE id_invoice=idInvoice;
        
    END IF;
    
    IF d>c THEN
        total:=d-c;
    
        UPDATE client SET credits=0
        WHERE email=email_client;
    
        UPDATE invoice SET delivery_fee=total
        WHERE id_invoice=idInvoice;
        
    END IF;

END;