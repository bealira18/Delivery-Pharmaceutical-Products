package lapr.project.ui;

import lapr.project.controller.AddCourierController;
import lapr.project.controller.AddDroneController;
import lapr.project.controller.AddParkController;
import lapr.project.controller.AddPharmacyController;
import lapr.project.data.CourierDB;
import lapr.project.data.DataHandler;
import lapr.project.model.*;

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
        //dh.scriptRunner("Documentation/BDDAD/LAPR3_DATABASE_CREATION.sql");
        //dh.scriptRunner("Documentation/BDDAD/LAPR3_INSERTS.sql");

        //AddCourierController
        //AddCourierController addCourierController = new AddCourierController();
        //addCourierController.addCourier("c1@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85);
        //System.out.println(addCourierController.findPharmacies());


        //AddDroneController
        /*AddDroneController addDroneController = new AddDroneController();
        Drone drone = new Drone(11, 1, 20, 40, 50, 60, 10, 100, 1);
        Drone drone1 = new Drone(12, 1, 20, 40, 50, 60, 10, 100, 1);
        Drone drone2 = new Drone(13, 1, 20, 40, 50, 60, 10, 100, 1);
        Drone drone3 = new Drone(14, 1, 20, 40, 50, 60, 10, 100, 1);
        Drone drone4 = new Drone(15, 1, 20, 40, 50, 60, 10, 100, 1);
        Drone drone5 = new Drone(16, 1, 20, 40, 50, 60, 10, 100, 1);

        addDroneController.addDrone(drone);
        addDroneController.addDrone(drone1);
        addDroneController.addDrone(drone2);
        addDroneController.addDrone(drone3);
        addDroneController.addDrone(drone4);
        addDroneController.addDrone(drone5);*/


        //AddParkController
        /*AddParkController addParkController = new AddParkController();
        Address a = new Address("teste", 0, 0, 0);
        Park park = new Park(3, 1, 1,1, "scooter", a);
        System.out.println(addParkController.addPark(park));
        Address a2 = new Address("isep", 0, 0, 0);
        Park park2 = new Park(3, 1, 1,1, "scooter", a2);
        System.out.println(addParkController.addPark(park2));*/

        /*Address a = new Address("Test", 0, 0, 0);
        Pharmacy p = new Pharmacy(0, "TestPharma", a);
        AddPharmacyController addPharmacyController = new AddPharmacyController();
        addPharmacyController.addPharmacy(a,p,2);*/
    }

}