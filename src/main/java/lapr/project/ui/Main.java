package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.*;
import lapr.project.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.GraphAlgorithms;

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
        //DataHandler dh = new DataHandler();
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
        /*Address a = new Address("gaia shopping", 0, 0, 0);
        Pharmacy p = new Pharmacy(2, "farmacia gaia shopping", a);
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
        /*AssignOrderToCourierScooterController assignOrderToCourierScooterController = new AssignOrderToCourierScooterController();
        System.out.println("\n\nAssignOrderToCourierScooterController");
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1,"a", LocalDate.now());
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        purchaseOrderList.add(purchaseOrder);
        System.out.println(assignOrderToCourierScooterController.addDeliveries(purchaseOrderList));*/
        //CreateInvoiceController ---------------------------------------------------------------------------------------
        /*ManageCreditsController manageCreditsController = new ManageCreditsController();
        manageCreditsController.setCreditValueDeliveryFee(5);
        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "client1@gmail.com", LocalDate.now());
        Invoice invoice;
        invoice = createInvoiceController.createInvoice(1, purchaseOrder);
        createInvoiceController.sendInvoiceByEmail(invoice);*/
        //GeographicalController ----------------------------------------------------------------------------------------
        /*GeographicalController geographicalController = new GeographicalController();
        System.out.println("\n\nGeographicalController");
        List<Address> addressList = geographicalController.getAddresses();
        List<Path> pathList = geographicalController.getPaths(addressList);
        List<Address> pharmaciesAddress = geographicalController.getPharmacyAddresses();
        //System.out.println(pharmaciesAddress);*/
        //GetDronesController -------------------------------------------------------------------------------------------
        /*GetDronesController getDronesController = new GetDronesController();
        getDronesController.getDrones(1);*/
        //GetProductsController
        /*GetProductsController getProductsController = new GetProductsController();
        System.out.println(getProductsController.getProducts());*/
        //GraphController ----------------------------------------------------------------------------------------------
        /*GraphController graphController = new GraphController();
        graphController.fillGraphDrone(addressList, pathList);
        System.out.println("\n\nGraphController");
        Address address = new Address("el corte ingles", 41.178333, 8.606389, 103);
        System.out.println(graphController.getNearestPharmacy(address, pharmaciesAddress));
        Address address2 = new Address("se do porto", 41.178333, 8.606389, 103);
        System.out.println(graphController.getNearestPharmacy(address2, pharmaciesAddress));*/
        //ManageCreditsController --------------------------------------------------------------------------------------
        /*ManageCreditsController manageCreditsController = new ManageCreditsController();
        CreditCard creditCard = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        Address address = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        Client client = new Client("client1@gmail.com", "qwerty", "user", 123456789, creditCard, address, 1);
        System.out.println("\n\nManageCreditsController");
        //manageCreditsController.setCreditConversionRatio(0.2);
        System.out.println(manageCreditsController.getCreditConversionRatio());
        System.out.println(manageCreditsController.addCreditsAfterPurchase(client, 30.00));
        System.out.println(manageCreditsController.addCreditsAfterPurchase(client, 22.00));
        //manageCreditsController.setCreditValueDeliveryFee(5);
        System.out.println(manageCreditsController.getCreditValueDeliveryFee());*/
        //NotifyClientController
        /*
          Falta testar o CheckIfIsEnoughStock
         */
 /*NotifyClientController notifyClientController = new NotifyClientController();
        System.out.println("\n\nNotifyClientController");
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1,"a", LocalDate.now());
        System.out.println(notifyClientController.notifyClientDeliveryRunStarts(purchaseOrder));*/
        //PurchaseItemsController
        /*O cenario ideal funciona como esperado,
          Falta a parte de se há ou nao stock disponivel...
         */
 /*PurchaseItemsController purchaseItemsController = new PurchaseItemsController();
        System.out.println("\n\nPurchaseItemsController");
        System.out.println(purchaseItemsController.getPharmacies());
        purchaseItemsController.getProductsFromPharmacy(1);
        System.out.println(purchaseItemsController.getProductCategories());
        ProductCategory productCategory = new ProductCategory(1,"a");
        System.out.println(purchaseItemsController.getProductsFromCategory(productCategory));
        Product product1 = new Product(1, "teste", 10, 3, 1);
        Product product2 = new Product(2, "teste1", 5, 2, 1);
        System.out.println(purchaseItemsController.addToBasket(product1, 3));
        System.out.println(purchaseItemsController.addToBasket(product2, 2));
        System.out.println(purchaseItemsController.addToBasket(product2, 1));
        System.out.println(purchaseItemsController.purchaseItems(1, 1, "client1@gmail.com"));*/
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
        /*Stock stock1 = new Stock(1, 1, 0);
        RemoveProductFromPharmacyCatalogController removeProductFromPharmacyCatalogController = new RemoveProductFromPharmacyCatalogController();
        System.out.println(removeProductFromPharmacyCatalogController.removeProductFromPharmacyCatalog(stock1));*/
        //SetMaximumPayloadController       -- definir nas application.properties?
        //UpdateCourierController
        /*UpdateCourierController updateCourierController = new UpdateCourierController();
        Courier c=new Courier("courier5@gmail.com","pass","name1",123456789,12345678,1,2.5);
        System.out.println(updateCourierController.updateCourier(c.getEmail(),c));*/
        //UpdateDeliveryFeeController
        /*UpdateDeliveryFeeController updateDeliveryFeeController = new UpdateDeliveryFeeController();
        updateDeliveryFeeController.updateDeliveryFee(2.90);
        System.out.println(PurchaseOrder.getDeliveryFee());*/
        //UpdateDroneController
        /*UpdateDroneController updateDroneController = new UpdateDroneController();
        Drone drone = new Drone(6, 1, 2, 0.5, 50, 60, 10, 100, 1, 1,1,1);
        System.out.println(updateDroneController.updateDrone(drone.getIdVehicle(), drone));*/
        //UpdateParkController
        /* UpdateParkController updateNrChargingStationsController = new UpdateParkController();
        System.out.println(updateNrChargingStationsController.updateNrChargingStations(1,1));*/
        //UpdatePharmacyController
        /*UpdatePharmacyController updatePharmacyController = new UpdatePharmacyController();
        System.out.println(updatePharmacyController.updatePharmacy(1, "blahh"));*/
        //UpdateProductController
        /*UpdateProductController updateProductController = new UpdateProductController();
        Product p=new Product(1,"name1",2.0,2.5,1);
        System.out.println(updateProductController.updateProduct(p.getId(), p));*/
        //UpdateScooterController
        /*UpdateScooterController updateScooterController = new UpdateScooterController();
        Scooter s=new Scooter(1,1,2.0,2.5,1,2.0,35.0,40.0,0,1);
        System.out.println(updateScooterController.updateScooter(s.getIdVehicle(), s));*/
        //UpdateStockController     -- still no orders in the system
        /*UpdateStockController updateStockController = new UpdateStockController();
        PurchaseOrder order = new PurchaseOrder(1,1, "emailTeste@gmail.com", LocalDate.now());
        System.out.println(updateStockController.updateProductStockAfterSale(1));*/
        //Dont uncomment this, poor andré
        /*EmailService eS = new EmailService();
        eS.sendEmail("11710601@isep.ipp.pt", "Hi", "Did you know that LA's full name is El Pueblo de Nuestra Señora la Reina de los Ángeles de Porciúncula?");*/

 /*AssemblyWatcher asmWatch = new AssemblyWatcher();

        Thread thr = new Thread(asmWatch);
        thr.setDaemon(true);
        thr.start();

        System.out.println("Type anything to stop program");
        Scanner in = new Scanner(System.in);
        in.nextLine();*/
        scenario1();

        /*VehicleDB vehicleDB = new VehicleDB();
        System.out.println(vehicleDB.getEmailNameFromParkedVehicleResponsible(101));
        System.out.println(vehicleDB.getEmailNameFromParkedVehicleResponsible(113));*/

    }

    public static void scenario1() {
        UpdateScooterController updateScooterController = new UpdateScooterController();
        ManageCreditsController manageCreditsController = new ManageCreditsController();
        manageCreditsController.setCreditConversionRatio(0.2);
        manageCreditsController.setCreditValueDeliveryFee(5);
        updateScooterController.updateScooterMaxPayload(5);
        System.out.println("filling graph");
        GeographicalController geographicalController = new GeographicalController();
        System.out.println("\n\nGeographicalController");
        List<Address> addressList = geographicalController.getAddresses();
        List<Path> pathList = geographicalController.getPaths(addressList);
        List<Address> pharmaciesAddress = geographicalController.getPharmacyAddresses();
        GraphController graphController = new GraphController();
        graphController.fillGraphDrone(addressList, pathList);
        graphController.fillGraphScooter(addressList, pathList);
        graphController.fillGraphDroneEnergy(addressList, pathList);
        graphController.fillGraphScooterEnergy(addressList, pathList);

        PurchaseItemsController purchaseItemsController = new PurchaseItemsController();
        System.out.println("\n\nPurchaseItemsController");
        Product product1 = new Product(1, "Ben-u-ron", 2.40, 0.1, 1);
        Product product2 = new Product(2, "Ibuprofeno", 4.70, 0.2, 1);
        Product product3 = new Product(6, "Fio Dentario Colgate", 3.49, 0.05, 2);
        System.out.println(purchaseItemsController.addToBasket(product1, 3));
        System.out.println(purchaseItemsController.addToBasket(product2, 2));
        System.out.println(purchaseItemsController.addToBasket(product2, 1));
        PurchaseOrder purchaseOrder = purchaseItemsController.purchaseItems(6, 1, "client1@gmail.com");
        System.out.println(purchaseOrder);

        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        Invoice invoice = createInvoiceController.createInvoice(1, purchaseOrder);
        createInvoiceController.sendInvoiceByEmail(invoice);
    }

    //We decide the address to visit and the pharmacy we go from.
    public static void scenarioOneDelivery() {

        //List of products when andre finishes his stuff
        List<Product> mexeTeAndre = new ArrayList<>();
        //Getting an address by ID maybe.
        Address clientAddress = new Address("Test", 0, 0, 0);
        GraphController gCont = new GraphController();
        GeographicalController geoCont = new GeographicalController();
        UpdateDroneController dCont = new UpdateDroneController();
        UpdateScooterController sCont = new UpdateScooterController();
        double scooterAverageEnergy = 700;

        List<Address> la = geoCont.getAddresses();
        List<Path> lp = geoCont.getPaths(la);
        gCont.fillGraphDrone(la, lp);
        gCont.fillGraphDroneEnergy(la, lp);
        gCont.fillGraphScooter(la, lp);
        gCont.fillGraphScooterEnergy(la, lp);

        //Need pharmacy address
        Address pharmacyAddress = new Address("Lol", 0, 0, 0);
        double sMaxPayload = sCont.getScooterMaxPayload();
        double dMaxPayload = dCont.getDroneMaxPayload();
        double productWeight = mexeTeAndre.stream().mapToDouble(Product::getWeight).sum();

        if (sMaxPayload >= productWeight) {

            LinkedList<Address> shortPathDistanceScooterGoing = new LinkedList<>();
            LinkedList<Address> shortPathDistanceScooterReturn = new LinkedList<>();
            LinkedList<Address> shortPathEnergyScooterGoing = new LinkedList<>();
            LinkedList<Address> shortPathEnergyScooterReturn = new LinkedList<>();

            double distanceGoing = gCont.getShortestPath(true, pharmacyAddress, clientAddress, shortPathDistanceScooterGoing);
            double distanceReturn = gCont.getShortestPath(true, clientAddress, pharmacyAddress, shortPathDistanceScooterReturn);
            double energyGoing = gCont.getShortestPathEnergy(true, pharmacyAddress, clientAddress, shortPathEnergyScooterGoing);
            double energyReturn = gCont.getShortestPathEnergy(true, clientAddress, pharmacyAddress, shortPathEnergyScooterReturn);

            if (shortPathDistanceScooterGoing.isEmpty()) {
                System.out.println("Scooter cannot travel from the pharmacy to the target client.");

            } else {

                if (energyGoing > scooterAverageEnergy) {
                    System.out.println("Scooter does not have the energy to travel from the pharmacy to the target client.");

                } else {
                    gCont.writePathToFile(true, "SingleDeliveryScenarioScooterGoing.csv", shortPathEnergyScooterGoing, distanceGoing / 1000, energyGoing, "Scooter");
                    System.out.println("Path exported to SingleDeliveryScenarioScooterGoing.csv -> Pharmacy -> Client (Scooter)");

                    if (shortPathDistanceScooterReturn.isEmpty()) {
                        System.out.println("Scooter cannot travel back to the pharmacy from the target client.");

                    } else {

                        if (energyGoing + energyReturn > scooterAverageEnergy) {

                            List<Address> listPharmacy = geoCont.getPharmacyAddresses();
                            Address nearestPharmacy = gCont.getNearestPharmacy(false, clientAddress, listPharmacy);

                            if (nearestPharmacy.equals(pharmacyAddress)) {
                                System.out.println("Scooter does not have energy to go back to the original pharmacy nor does it have a pharmacy to charge in.");

                            } else {
                                LinkedList<Address> shortPathDistanceScooterClientToPharmacy = new LinkedList<>();
                                LinkedList<Address> shortPathEnergyScooterClientToPharmacy = new LinkedList<>();

                                double distanceToPharmacy = gCont.getShortestPath(true, clientAddress, nearestPharmacy, shortPathDistanceScooterClientToPharmacy);
                                double energyToPharmacy = gCont.getShortestPath(true, clientAddress, nearestPharmacy, shortPathEnergyScooterClientToPharmacy);

                                if (energyGoing + energyToPharmacy > scooterAverageEnergy) {
                                    System.out.println("Scooter does not have energy to go back to the original pharmacy nor does it have energy to reach the nearest pharmacy to charge in.");

                                } else {
                                    gCont.writePathToFile(true, "SingleDeliveryScenarioScooterToPharmacyToCharge.csv", shortPathEnergyScooterClientToPharmacy, distanceToPharmacy / 1000, energyToPharmacy, "Scooter");
                                    System.out.println("Path exported to SingleDeliveryScenarioScooterGoingToPharmacyToCharge.csv -> Client - Pharmacy (PitStop) (Scooter)");

                                    //INSERT HERE THE CHARGING SCENARIO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    LinkedList<Address> shortPathDistanceScooterPharmacyToPharmacy = new LinkedList<>();
                                    LinkedList<Address> shortPathEnergyScooterPharmacyToPharmacy = new LinkedList<>();

                                    double distanceToOrigPharmacy = gCont.getShortestPath(true, nearestPharmacy, pharmacyAddress, shortPathDistanceScooterPharmacyToPharmacy);
                                    double energyToOrigPharmacy = gCont.getShortestPath(true, clientAddress, nearestPharmacy, shortPathEnergyScooterPharmacyToPharmacy);

                                    if (energyToOrigPharmacy > scooterAverageEnergy) {
                                        System.out.println("Scooter does not have the energy to go back to the original pharmacy from the one it has charged in.");

                                    } else {
                                        gCont.writePathToFile(true, "SingleDeliveryScenarioScooterBackToPharmacy.csv", shortPathEnergyScooterPharmacyToPharmacy, distanceToOrigPharmacy / 1000, energyToOrigPharmacy, "Scooter");
                                        System.out.println("Path exported to SingleDeliveryScenarioScooterBackToPharmacy.csv -> Pharmacy (PitStop) - Pharmacy (Scooter)");
                                        System.out.println("Scenario Ended.");
                                    }
                                }
                            }
                        } else {
                            gCont.writePathToFile(true, "SingleDeliveryScenarioScooterReturn.csv", shortPathEnergyScooterReturn, distanceReturn / 1000, energyReturn, "Scooter");
                            System.out.println("Path exported to SingleDeliveryScenarioScooterReturn.csv -> Client - Pharmacy (Scooter)");
                            System.out.println("Scenario Ended");
                        }
                    }
                }
            }
        }
    }
}
