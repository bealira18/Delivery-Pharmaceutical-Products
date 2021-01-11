package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddPharmacyControllerTest {

    private static AddPharmacyController pCont;

    public AddPharmacyControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Pharmacy p = new Pharmacy(0, "TestPharma", new Address("TestAddress", 0, 0, 0));
        PharmacyDB pDB = mock(PharmacyDB.class);
        int limit = 2;

        when(pDB.addPharmacy(p, limit)).thenReturn(Boolean.TRUE);

        pCont = new AddPharmacyController();
        pCont = new AddPharmacyController(pDB);
    }

    /**
     * Test of addPharmacy method, of class PharmacyController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddPharmacy() throws Exception {

        System.out.println("addPharmacy");

        Pharmacy p = new Pharmacy(0, "TestPharma", new Address("TestAddress", 0, 0, 0));
        int limit = 2;

        boolean expResult = true;
        boolean result = pCont.addPharmacy(p, limit);
        assertEquals(expResult, result);

        PharmacyDB pDB = mock(PharmacyDB.class);
        when(pDB.addPharmacy(new Pharmacy(0, "TestPharma", new Address("TestAddress", 0, 0, 0)), limit)).thenReturn(Boolean.FALSE);
        AddPharmacyController pCont1 = new AddPharmacyController(pDB);

        expResult = false;
        result = pCont1.addPharmacy(p, limit);
        assertEquals(expResult, result);

        PharmacyDB pDB1 = mock(PharmacyDB.class);
        when(pDB1.addPharmacy(new Pharmacy(0, "TestPharma", new Address("TestAddress", 0, 0, 0)), limit)).thenThrow(new SQLException());
        AddPharmacyController pCont2 = new AddPharmacyController(pDB);

        expResult = false;
        result = pCont2.addPharmacy(p, limit);
        assertEquals(expResult, result);
    }
}
