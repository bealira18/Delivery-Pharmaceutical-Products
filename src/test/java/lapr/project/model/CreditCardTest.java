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
public class CreditCardTest {
    
    public CreditCardTest() {
    }

    /**
     * Test of getCreditCardNumber method, of class CreditCard.
     */
    @Test
    public void testGetCreditCardNumber() {
        System.out.println("getCreditCardNumber");
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        long expResult = 5295360011327825L;
        long result = instance.getCreditCardNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValidityDate method, of class CreditCard.
     */
    @Test
    public void testGetValidityDate() {
        System.out.println("getValidityDate");
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        LocalDate expResult = LocalDate.of(2077, Month.MARCH, 1);
        LocalDate result = instance.getValidityDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCcv method, of class CreditCard.
     */
    @Test
    public void testGetCcv() {
        System.out.println("getCcv");
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);;
        short expResult = 454;
        short result = instance.getCcv();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCreditCardNumber method, of class CreditCard.
     */
    @Test
    public void testSetCreditCardNumber() {
        System.out.println("setCreditCardNumber");
        long creditCardNumber = 9640434369499902L;
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);;
        instance.setCreditCardNumber(creditCardNumber);
        long result = instance.getCreditCardNumber();
        assertEquals(creditCardNumber, result);
    }

    /**
     * Test of setValidityDate method, of class CreditCard.
     */
    @Test
    public void testSetValidityDate() {
        System.out.println("setValidityDate");
        LocalDate validityDate = LocalDate.of(2076, Month.FEBRUARY, 1);
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        instance.setValidityDate(validityDate);
        LocalDate result = instance.getValidityDate();
        assertEquals(validityDate, result);
    }

    /**
     * Test of setCcv method, of class CreditCard.
     */
    @Test
    public void testSetCcv() {
        System.out.println("setCcv");
        short ccv = 666;
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        instance.setCcv(ccv);
        short result = instance.getCcv();
        assertEquals(ccv, result);
    }

    /**
     * Test of toString method, of class CreditCard.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        String expResult = "CreditCard{creditCardNumber=5295360011327825, validityDate=2077-03-01, ccv=454}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CreditCard.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        int expResult = 343673454;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CreditCard.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        CreditCard instance = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        boolean result = instance.equals(instance);
        assertEquals(true, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "Meme";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new CreditCard(9640434369499902L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        result = instance.equals(obj);
        assertEquals(true, result);
    }    
}
