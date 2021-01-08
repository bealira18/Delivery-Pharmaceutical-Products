package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PharmacyControllerTest {

    private static PharmacyController pCont;

    public PharmacyControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Pharmacy p = new Pharmacy(0, "TestPharma", "TestAddress");
        PharmacyDB pDB = mock(PharmacyDB.class);
        int limit = 2;

        when(pDB.addPharmacy(p, limit)).thenReturn(Boolean.TRUE);

        pCont = new PharmacyController();
        pCont = new PharmacyController(pDB);
    }

    /**
     * Test of addPharmacy method, of class PharmacyController.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddPharmacy() throws Exception {

        System.out.println("addPharmacy");

        Pharmacy p = new Pharmacy(0, "TestPharma", "TestAddress");
        int limit = 2;

        boolean expResult = true;
        boolean result = pCont.addPharmacy(p, limit);
        assertEquals(expResult, result);

        PharmacyDB pDB = mock(PharmacyDB.class);
        when(pDB.addPharmacy(new Pharmacy(0, "TestPharma", "TestAddress"), limit)).thenReturn(Boolean.FALSE);
        PharmacyController pCont1 = new PharmacyController(pDB);

        expResult = false;
        result = pCont1.addPharmacy(p, limit);
        assertEquals(expResult, result);

        PharmacyDB pDB1 = mock(PharmacyDB.class);
        when(pDB1.addPharmacy(new Pharmacy(0, "TestPharma", "TestAddress"), limit)).thenThrow(new SQLException());
        PharmacyController pCont2 = new PharmacyController(pDB);

        expResult = false;
        result = pCont2.addPharmacy(p, limit);
        assertEquals(expResult, result);
    }
}
