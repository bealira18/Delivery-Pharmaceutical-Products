CREATE OR REPLACE PROCEDURE useCredits(email_client client.email%type, idInvoice invoice.id_invoice%type) IS

c INTEGER;
d INTEGER;
total INTEGER;

begin

    select credits into c
    from client
    where email=email_client;
    
    select delivery_fee into d
    from invoice
    where id_invoice=idInvoice;
    
    if c>=d then
        total:=c-d;
    
        update client SET credits=total
        where email=email_client;
    
        update invoice SET delivery_fee=0
        where id_invoice=idInvoice;
        
    end if;
    
    if d>c then
        total:=d-c;
    
        update client SET credits=0
        where email=email_client;
    
        update invoice SET delivery_fee=total
        where id_invoice=idInvoice;
        
    end if;

end;
/