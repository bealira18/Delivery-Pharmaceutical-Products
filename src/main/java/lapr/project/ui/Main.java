package lapr.project.ui;

import lapr.project.controller.AddCourierController;
import lapr.project.controller.AddPharmacyController;
import lapr.project.data.CourierDB;
import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


class Main {


    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        //load database properties

        try {
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initial Database Setup
        DataHandler dh = new DataHandler();
        dh.scriptRunner("Documentation/BDDAD/LAPR3_DATABASE_CREATION.sql");
        dh.scriptRunner("Documentation/BDDAD/LAPR3_INSERTS.sql");


        AddCourierController addCourierController = new AddCourierController();
        addCourierController.addCourier("c1@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85);

        /*Address a = new Address("Test", 0, 0, 0);
        Pharmacy p = new Pharmacy(0, "TestPharma", a);
        AddPharmacyController addPharmacyController = new AddPharmacyController();
        addPharmacyController.addPharmacy(a,p,2);*/
    }

}