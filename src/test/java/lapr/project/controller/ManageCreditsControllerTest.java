package lapr.project.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import lapr.project.data.ClientDB;
import lapr.project.data.SettingsHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ManageCreditsControllerTest {

    public static ManageCreditsController mcC;

    public ManageCreditsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        SettingsHandler sh = mock(SettingsHandler.class);
        ClientDB cDB = mock(ClientDB.class);

        mcC = new ManageCreditsController();
        mcC = new ManageCreditsController(sh, cDB);

    }

    /**
     * Test of addCreditsAfterPurchase method, of class ManageCreditsController.
     */
    @Test
    public void testAddCreditsAfterPurchase() throws SQLException {
        System.out.println("addCreditsAfterPurchase");
        System.setProperty("client.credits.purchase.ratio", "0.5");
        Client client = new Client("a@b.c", "qwerty", "Joaquim Alberto", 123456789, new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454), new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411), 0);;
        double purchaseAmount = 3.99;
        int expResult = 1;
        int result = mcC.addCreditsAfterPurchase(client, purchaseAmount);
        assertEquals(expResult, result);
        assertEquals(expResult, client.getCredits());

        purchaseAmount = 6.99;
        expResult = 3;
        result = mcC.addCreditsAfterPurchase(client, purchaseAmount);
        assertEquals(expResult, result);
        assertEquals(expResult + 1, client.getCredits());
    }

    /**
     * Test of getCreditConversionRatio method, of class
     * ManageCreditsController.
     */
    @Test
    public void testGetCreditConversionRatio() {

        System.out.println("getCreditConversionRatio");
        System.setProperty("client.credits.purchase.ratio", "0.0");
        double expResult = 0.0;
        double result = mcC.getCreditConversionRatio();
        assertEquals(expResult, result, 0.01);

        System.setProperty("client.credits.purchase.ratio", "0.5");
        expResult = 0.5;
        result = mcC.getCreditConversionRatio();
        assertEquals(expResult, result, 0.01);

        System.setProperty("client.credits.purchase.ratio", "1");
        expResult = 1.0;
        result = mcC.getCreditConversionRatio();
        assertEquals(expResult, result, 0.01);

        System.setProperty("client.credits.purchase.ratio", "-1.5");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.getCreditConversionRatio();
        });
        assertEquals("The conversion ratio must be a value between 0 and 1 inclusive. Please check your configuration file.", ex.getMessage());

        System.setProperty("client.credits.purchase.ratio", "1.1");
        ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.getCreditConversionRatio();
        });
        assertEquals("The conversion ratio must be a value between 0 and 1 inclusive. Please check your configuration file.", ex.getMessage());
    }

    /**
     * Test of setCreditConversionRatio method, of class
     * ManageCreditsController.
     */
    @Test
    public void testSetCreditConversionRatio() {

        System.out.println("setCreditConversionRatio");
        System.setProperty("client.credits.purchase.ratio", "5.4");
        double newRatio = 1.0;
        mcC.setCreditConversionRatio(newRatio);
        double result = Double.parseDouble(System.getProperty("client.credits.purchase.ratio"));
        assertEquals(newRatio, result, 0.01);

        newRatio = 0;
        mcC.setCreditConversionRatio(newRatio);
        result = Double.parseDouble(System.getProperty("client.credits.purchase.ratio"));
        assertEquals(newRatio, result, 0.01);

        newRatio = 0.5;
        mcC.setCreditConversionRatio(newRatio);
        result = Double.parseDouble(System.getProperty("client.credits.purchase.ratio"));
        assertEquals(newRatio, result, 0.01);

        final double newRatio2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.setCreditConversionRatio(newRatio2);
        });
        assertEquals("The conversion ratio must be a value between 0 and 1 inclusive.", ex.getMessage());

        final double newRatio3 = 1.1;
        ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.setCreditConversionRatio(newRatio3);
        });
        assertEquals("The conversion ratio must be a value between 0 and 1 inclusive.", ex.getMessage());
    }

    /**
     * Test of getCreditValueDeliveryFee method, of class
     * ManageCreditsController.
     */
    @Test
    public void testGetCreditValueDeliveryFee() {

        System.out.println("getCreditValueDeliveryFee");
        System.setProperty("client.credits.delivery.fee.payment", "1");
        int expResult = 1;
        int result = mcC.getCreditValueDeliveryFee();
        assertEquals(expResult, result);

        System.setProperty("client.credits.delivery.fee.payment", "0");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.getCreditValueDeliveryFee();
        });
        assertEquals("The amount of credits to pay a delivery fee cannot be negative or zero. Please check your configuration file.", ex.getMessage());

        System.setProperty("client.credits.delivery.fee.payment", "-1");
        ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.getCreditValueDeliveryFee();
        });
        assertEquals("The amount of credits to pay a delivery fee cannot be negative or zero. Please check your configuration file.", ex.getMessage());
    }

    /**
     * Test of setCreditValueDeliveryFee method, of class
     * ManageCreditsController.
     */
    @Test
    public void testSetCreditValueDeliveryFee() {

        System.out.println("setCreditValueDeliveryFee");
        int newCredits = 2;
        mcC.setCreditValueDeliveryFee(newCredits);
        int result = Integer.parseInt(System.getProperty("client.credits.delivery.fee.payment"));
        assertEquals(newCredits, result);

        final int newCredits2 = 0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.setCreditValueDeliveryFee(newCredits2);
        });
        assertEquals("The amount of credits to pay a delivery fee cannot be negative or zero.", ex.getMessage());

        final int newCredits3 = -1;
        ex = assertThrows(IllegalArgumentException.class, () -> {
            mcC.setCreditValueDeliveryFee(newCredits3);
        });
        assertEquals("The amount of credits to pay a delivery fee cannot be negative or zero.", ex.getMessage());
    }
}
