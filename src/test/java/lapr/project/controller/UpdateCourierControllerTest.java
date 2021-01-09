package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Courier;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdateCourierControllerTest {

    private static UpdateCourierController cPC;

    public UpdateCourierControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        CourierDB cDB = mock(CourierDB.class);

        cPC = new UpdateCourierController();
        cPC = new UpdateCourierController(cDB);
    }

    @Test
    void testUpdateCourier1(){
        Courier c=new Courier("email","pass","name",123456789,12345678,1,2.5);
        boolean actual=cPC.updateCourier("email1",c);
        assertFalse(actual); //porque não existem estafetas
    }

    @Test
    void testUpdateCourier2(){
        boolean actual=cPC.updateCourier("email",null);
        assertFalse(actual);
    }

}
