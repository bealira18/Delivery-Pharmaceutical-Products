package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.CourierDB;
import lapr.project.data.DataHandler;
import lapr.project.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.EmailService;
import lapr.project.data.SettingsHandler;


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

        SettingsHandler sH = new SettingsHandler();
        sH.loadSettings(SettingsHandler.SETTINGS_FILE);
        
        System.out.println(System.getProperties());
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);
        
        //Initial Database Setup
        DataHandler dh = new DataHandler();
        //dh.scriptRunner("Documentation/BDDAD/LAPR3_DATABASE_CREATION.sql");
        //dh.scriptRunner("Documentation/BDDAD/LAPR3_INSERTS.sql");


        //AddAdministratorController
        /*AddAdministratorController addAdministratorController = new AddAdministratorController();
        Administrator administrator1 = new Administrator("teste@gmail.com", "teste", 2, "teste", 223445365, 11254852163L);
        System.out.println(addAdministratorController.addAdministrator(administrator1));*/


        //AddCourierController
        /*AddCourierController addCourierController = new AddCourierController();
        System.out.println(addCourierController.addCourier("courier6@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85));
        System.out.println(addCourierController.findPharmacies());*/


        //AddDroneController
        /*AddDroneController addDroneController = new AddDroneController();
        Drone drone = new Drone(11, 1, 20, 40, 0, 60, 10, 100, 1, 1, 1, 1);
        Drone drone1 = new Drone(12, 1, 20, 40, 50, 60, 10, 100, 1, 1, 1, 1);
        Drone drone2 = new Drone(13, 1, 20, 40, 50, 60, 10, 100, 1, 1, 1, 1);
        Drone drone3 = new Drone(14, 1, 20, 40, 50, 60, 10, 100, 1, 1, 1, 1);
        Drone drone4 = new Drone(15, 1, 20, 40, 50, 60, 10, 100, 1, 1, 1, 1);
        Drone drone5 = new Drone(16, 1, 20, 40, 50, 60, 10, 100, 1, 1, 1, 1);

        addDroneController.addDrone(drone);
        addDroneController.addDrone(drone1);
        addDroneController.addDrone(drone2);
        addDroneController.addDrone(drone3);
        addDroneController.addDrone(drone4);
        addDroneController.addDrone(drone5);*/


        //AddParkController
        /*AddParkController addParkController = new AddParkController();
        Address a = new Address("teste", 0, 0, 0);
        Park park = new Park(3, 1, 1,1, "scooter", a, 5000);
        System.out.println(addParkController.addPark(park));
        Address a2 = new Address("isep", 0, 0, 0);
        Park park2 = new Park(3, 1, 1,1, "scooter", a2, 50000);
        System.out.println(addParkController.addPark(park2));*/


        //AddPharmacyController
        /*Address a = new Address("testeAddress", 0, 0, 0);
        Pharmacy p = new Pharmacy(0, "TestPharma", a);
        AddPharmacyController addPharmacyController = new AddPharmacyController();
        System.out.println(addPharmacyController.addPharmacy(a,p,2,2));*/


        //AddProductController
        /*AddProductController addProductController = new AddProductController();
        Product product1 = new Product(0, "NameTest", 10, 20, 1);
        System.out.println(addProductController.addProduct(product1));*/


        //AddProductToPhamacyCatalogController
        /*AddProductToPharmacyCatalogController addProductToPharmacyCatalogController = new AddProductToPharmacyCatalogController();
        Stock stock1 = new Stock(1, 3, 0);
        System.out.println(addProductToPharmacyCatalogController.addProductToPharmacyCatalog(stock1));*/


        //AddScooterController
        /*AddScooterController addScooterController = new AddScooterController();
        Scooter scooter1 = new Scooter(0, 1, 20, 40, 50, 60, 10, 100, 1, 1);
        System.out.println(addScooterController.addScooter(scooter1));*/


        //AssignOrderToCourierScooterController --------------------------------------------------------------------------


        //CreateInvoiceController ---------------------------------------------------------------------------------------


        //GeographicalController ----------------------------------------------------------------------------------------


        //GetDronesController -------------------------------------------------------------------------------------------
        /*GetDronesController getDronesController = new GetDronesController();
        getDronesController.getDrones(1);*/


        //GetProductsController
        /*GetProductsController getProductsController = new GetProductsController();
        System.out.println(getProductsController.getProducts());*/


        //GraphController ----------------------------------------------------------------------------------------------
        GraphController graphController = new GraphController();



        //ManageCreditsController --------------------------------------------------------------------------------------


        //PurchaseItemsControlle
        PurchaseItemsController purchaseItemsController = new PurchaseItemsController();
        //purchaseItemsController


        //RegisterClientController
        /*RegisterClientController registerClientController = new RegisterClientController();
        String email = "test3@email.com";
        String password = "qwerty";
        String name = "Joaquim Alberto";
        int nif = 123456787;
        CreditCard creditCard = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        Address address = new Address("isep", 41.15796537787468, -8.62910514603121, 5.200514144411);
        System.out.println(registerClientController.addClient(email, password, name, nif, creditCard, address));*/


        //RemoveProductFromPharmacyCatalogController
        /*Stock stock1 = new Stock(1, 1, 10);
        RemoveProductFromPharmacyCatalogController removeProductFromPharmacyCatalogController = new RemoveProductFromPharmacyCatalogController();
        System.out.println(removeProductFromPharmacyCatalogController.removeProductFromPharmacyCatalog(stock1));*/


        //SetDeliveryFeeController      --- not saving after execution finished
        /*SetDeliveryFeeController setDeliveryFeeController = new SetDeliveryFeeController();
        System.out.println(setDeliveryFeeController.setDeliveryFee(2.90));
        System.out.println(new PurchaseOrder(1,1,"a", LocalDate.now()).getDeliveryFee());*/


        //SetMaximumPayloadController       -- definir nas application.properties?


        //UpdateCourierController
        /*UpdateCourierController updateCourierController = new UpdateCourierController();
        Courier c=new Courier("courier5@gmail.com","pass","name1",123456789,12345678,1,2.5);
        System.out.println(updateCourierController.updateCourier(c.getEmail(),c));*/


        //UpdateDeliveryFeeController           --- correr isto removeu a ligaçao à bddad... ta a apagar as coisas das properties......
        //UpdateDeliveryFeeController updateDeliveryFeeController = new UpdateDeliveryFeeController();
        //updateDeliveryFeeController.updateDeliveryFee(2.90);


        //UpdateDroneController
        /*UpdateDroneController updateDroneController = new UpdateDroneController();
        Drone drone = new Drone(6, 1, 2, 40, 50, 60, 10, 100, 1);
        System.out.println(updateDroneController.updateDrone(drone.getIdVehicle(), drone));*/


        //UpdateNrChargingStationsController
        /*UpdateNrChargingStationsController updateNrChargingStationsController = new UpdateNrChargingStationsController();
        System.out.println(updateNrChargingStationsController.updateNrChargingStations(1,5));*/


        //UpdatePharmacyController
        /*UpdatePharmacyController updatePharmacyController = new UpdatePharmacyController();
        System.out.println(updatePharmacyController.updatePharmacy(1, "blahh"));*/


        //UpdateProductController
        /*UpdateProductController updateProductController = new UpdateProductController();
        Product p=new Product(1,"name1",2.0,2.5,1);
        System.out.println(updateProductController.updateProduct(p.getId(), p));*/


        //UpdateScooterController
        /*UpdateScooterController updateScooterController = new UpdateScooterController();
        Scooter s=new Scooter(1,1,2.0,2.5,1,2.0,35.0,40.0,0);
        System.out.println(updateScooterController.updateScooter(s.getIdVehicle(), s));*/


        //UpdateStockController     -- still no orders in the system
        /*UpdateStockController updateStockController = new UpdateStockController();
        PurchaseOrder order = new PurchaseOrder(1,1, "emailTeste@gmail.com", LocalDate.now());
        System.out.println(updateStockController.updateProductStockAfterSale(1));*/


        //UseCreditsController      ------------------------------------------------------------------------------------
        
        //Dont uncomment this, poor andré
//        EmailService eS = new EmailService();
//        eS.sendEmail("11710602@isep.ipp.pt", "Hi", "Did you know that LA's full name is El Pueblo de Nuestra Señora la Reina de los Ángeles de Porciúncula?");
        
    }

}