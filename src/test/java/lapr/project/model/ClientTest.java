/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ricardo
 */
public class ClientTest {
    
    public ClientTest() {
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 0);
        String expResult = "Joaquim Alberto";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Client.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 0);
        int expResult = 123456789;
        int result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditCard method, of class Client.
     */
    @Test
    public void testGetCreditCard() {
        System.out.println("getCreditCard");
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 0);
        CreditCard expResult = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        CreditCard result = instance.getCreditCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Client.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 0);
        Address expResult = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        Address result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCredits method, of class Client.
     */
    @Test
    public void testGetCredits() {
        System.out.println("getCredits");
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        int expResult = 66;
        int result = instance.getCredits();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Client.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "NotAName";
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of setNif method, of class Client.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        int nif = 0;
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        instance.setNif(nif);
        int result = instance.getNif();
        assertEquals(nif, result);
    }

    /**
     * Test of setCreditCard method, of class Client.
     */
    @Test
    public void testSetCreditCard() {
        System.out.println("setCreditCard");
        CreditCard creditCard = new CreditCard(5295360011327825L, LocalDate.of(2076, Month.OCTOBER, 1), (short) 666);
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        instance.setCreditCard(creditCard);
        CreditCard result = instance.getCreditCard();
        assertEquals(creditCard, result);
    }

    /**
     * Test of setAddress method, of class Client.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        Address address = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        instance.setAddress(address);
        Address result = instance.getAddress();
        assertEquals(address, result);
    }

    /**
     * Test of setCredits method, of class Client.
     */
    @Test
    public void testSetCredits() {
        System.out.println("setCredits");
        int credits = 666;
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        instance.setCredits(credits);
        int result = instance.getCredits();
        assertEquals(credits, result);
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Client instance = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 66);
        String expResult = "Client{email=a@b.c, name=Joaquim Alberto, nif=123456789, creditCard=CreditCard{creditCardNumber=5295360011327825, validityDate=2077-03-01, ccv=454}, address=Address{description=Rua Joaquim, 542, latitude=41.15796537787468, longitude=-8.62910514603121, altitude=5.200514144411}, credits=66}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
