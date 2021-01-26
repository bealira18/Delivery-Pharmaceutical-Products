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
import lapr.project.utils.PathAlgorithms;

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

        setUpProperties();
        scenario1();
	   scenarioOneDelivery();
        scenarioMultipleDeliveries();

        //scenario1();
        //parkingScenario();
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
 /*VehicleDB vehicleDB = new VehicleDB();
        System.out.println(vehicleDB.getEmailNameFromParkedVehicleResponsible(101));
        System.out.println(vehicleDB.getEmailNameFromParkedVehicleResponsible(113));*/

 /*AddScooterController addScooterController = new AddScooterController();
        Scooter scooter1 = new Scooter(0, 1, 20, 40, 50, 60, 10, 100, 1, 1);
        System.out.println(addScooterController.addScooter(scooter1));*/
    }

    public static void setUpProperties() {
        UpdateScooterController updateScooterController = new UpdateScooterController();
        UpdateDroneController updateDroneController = new UpdateDroneController();
        ManageCreditsController manageCreditsController = new ManageCreditsController();
        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        UpdateDeliveryFeeController updateDeliveryFeeController = new UpdateDeliveryFeeController();
        manageCreditsController.setCreditConversionRatio(0.2);
        manageCreditsController.setCreditValueDeliveryFee(5);
        updateScooterController.updateScooterMaxPayload(3);
        updateDroneController.updateDroneMaxPayload(1);
        createInvoiceController.updateIVA(0.23);
        updateDeliveryFeeController.updateDeliveryFee(2.9);
    }

    public static void scenario1() {
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
        PurchaseOrder purchaseOrder = purchaseItemsController.purchaseItems(1, 1, "client1@gmail.com", graphController.getGraphScooterEnergy());
        System.out.println(purchaseOrder);

        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        Invoice invoice = createInvoiceController.createInvoice(1, purchaseOrder);
        createInvoiceController.sendInvoiceByEmail(invoice);
    }

    //We decide the address to visit and the pharmacy we go from. Serves as template for multiple scenarios, just have to adjust values to trigger them.
    public static void scenarioOneDelivery() {

        GraphController gCont = new GraphController();
        GeographicalController geoCont = new GeographicalController();
        UpdateDroneController dCont = new UpdateDroneController();
        UpdateScooterController sCont = new UpdateScooterController();
        CreateDeliveryController cdCont = new CreateDeliveryController();
        CourierDB cDB = new CourierDB();
        AddressDB aDB = new AddressDB();

        Address clientAddress = new Address("feup", 41.1775, 8.598056, 111);
        Courier c = cDB.getCourier("courier3@gmail.com");
        Scooter s = cdCont.getHighestBatteryScooter(1);
        Drone d = cdCont.getHighestBatteryDrone(1);
        // Receber a lista de produtos? Não sei.
        List<Product> mexeTeAndre = new ArrayList<>();
        // Delete this after arranging proper data.
        mexeTeAndre.add(new Product(1, "", 0.0, sCont.getScooterMaxPayload() / 1000, 1));
        // Above
        List<Address> la = geoCont.getAddresses();
        List<Path> lp = geoCont.getPaths(la);
        gCont.fillGraphDrone(la, lp);
        gCont.fillGraphDroneEnergy(la, lp);
        gCont.fillGraphScooter(la, lp);
        gCont.fillGraphScooterEnergy(la, lp);

        Address pharmacyAddress = aDB.getAddressPharmacyById(1);
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
            gCont.getShortestPathEnergy(true, pharmacyAddress, clientAddress, shortPathEnergyScooterGoing);
            gCont.getShortestPathEnergy(true, clientAddress, pharmacyAddress, shortPathEnergyScooterReturn);
            double energyGoing = PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathEnergyScooterGoing, c, s, mexeTeAndre);
            double energyReturn = PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathEnergyScooterReturn, c, s, mexeTeAndre);

            if (shortPathDistanceScooterGoing.isEmpty()) {
                System.out.println("Scooter cannot travel from the pharmacy to the target client.");

            } else {

                if (energyGoing > s.getCurrentBattery()) {
                    System.out.println("Scooter does not have the energy to travel from the pharmacy to the target client.");

                } else {
                    gCont.writePathToFile("SingleDeliveryScenarioScooterEnergyGoing.csv", shortPathEnergyScooterGoing, PathAlgorithms.calcTotalDistance(shortPathEnergyScooterGoing) / 1000, energyGoing, c, s, mexeTeAndre);
                    gCont.writePathToFile("SingleDeliveryScenarioScooterDistanceGoing.csv", shortPathDistanceScooterGoing, distanceGoing / 1000, PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathDistanceScooterGoing, c, s, mexeTeAndre), c, s, mexeTeAndre);
                    System.out.println("Scooter can traverse to the target client. Paths exported to 'SingleDeliveryScenarioScooterDistance/EnergyGoing.csv'");

                    if (shortPathDistanceScooterReturn.isEmpty()) {
                        System.out.println("Scooter cannot travel back to the pharmacy from the target client.");

                    } else {

                        if (energyGoing + energyReturn > s.getCurrentBattery()) {

                            List<Address> listPharmacy = geoCont.getPharmacyAddresses();
                            Address nearestPharmacy = gCont.getNearestPharmacy(false, clientAddress, listPharmacy);

                            if (nearestPharmacy.equals(pharmacyAddress)) {
                                System.out.println("Scooter does not have energy to go back to the original pharmacy nor does it have a pharmacy to charge in.");

                            } else {
                                LinkedList<Address> shortPathDistanceScooterClientToPharmacy = new LinkedList<>();
                                LinkedList<Address> shortPathEnergyScooterClientToPharmacy = new LinkedList<>();

                                double distanceToPharmacy = gCont.getShortestPath(true, clientAddress, nearestPharmacy, shortPathDistanceScooterClientToPharmacy);
                                gCont.getShortestPath(true, clientAddress, nearestPharmacy, shortPathEnergyScooterClientToPharmacy);
                                double energyToPharmacy = PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathEnergyScooterClientToPharmacy, c, s, mexeTeAndre);

                                if (energyGoing + energyToPharmacy > s.getCurrentBattery()) {
                                    System.out.println("Scooter does not have energy to go back to the original pharmacy nor does it have energy to reach the nearest pharmacy to charge in.");

                                } else {
                                    gCont.writePathToFile("SingleDeliveryScenarioScooterEnergyToPharmacyToCharge.csv", shortPathEnergyScooterClientToPharmacy, PathAlgorithms.calcTotalDistance(shortPathEnergyScooterClientToPharmacy) / 1000, energyToPharmacy, c, s, mexeTeAndre);
                                    gCont.writePathToFile("SingleDeliveryScenarioScooterDistanceToPharmacyToCharge.csv", shortPathDistanceScooterClientToPharmacy, distanceToPharmacy / 1000, PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathDistanceScooterClientToPharmacy, c, s, mexeTeAndre), c, s, mexeTeAndre);
                                    System.out.println("Scooter can traverse to a pharmacy in order to charge. Paths exported to 'SingleDeliveryScenarioScooterEnergy/DistanceToPharmacyToCharge.csv'");

                                    //INSERT HERE THE CHARGING SCENARIO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    LinkedList<Address> shortPathDistanceScooterPharmacyToPharmacy = new LinkedList<>();
                                    LinkedList<Address> shortPathEnergyScooterPharmacyToPharmacy = new LinkedList<>();

                                    double distanceToOrigPharmacy = gCont.getShortestPath(true, nearestPharmacy, pharmacyAddress, shortPathDistanceScooterPharmacyToPharmacy);
                                    gCont.getShortestPath(true, clientAddress, nearestPharmacy, shortPathEnergyScooterPharmacyToPharmacy);
                                    double energyToOrigPharmacy = PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathEnergyScooterPharmacyToPharmacy, c, s, mexeTeAndre);

                                    if (energyToOrigPharmacy > s.getCurrentBattery()) {
                                        System.out.println("Scooter does not have the energy to go back to the original pharmacy from the one it has charged in.");

                                    } else {
                                        gCont.writePathToFile("SingleDeliveryScenarioScooterEnergyBackToPharmacy.csv", shortPathEnergyScooterPharmacyToPharmacy, PathAlgorithms.calcTotalDistance(shortPathEnergyScooterPharmacyToPharmacy) / 1000, energyToOrigPharmacy, c, s, mexeTeAndre);
                                        gCont.writePathToFile("SingleDeliveryScenarioScooterDistanceBackToPharmacy.csv", shortPathDistanceScooterPharmacyToPharmacy, distanceToOrigPharmacy / 1000, PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathDistanceScooterPharmacyToPharmacy, c, s, mexeTeAndre), c, s, mexeTeAndre);
                                        System.out.println("Scooter can traverse from the pharmacy it has charge in to the original pharmacy. Paths exported to 'SingleDeliveryScenarioScooterEnergy/DistancePharmacyToPharmacy.csv'");
                                    }
                                }
                            }
                        } else {
                            gCont.writePathToFile("SingleDeliveryScenarioScooterEnergyReturn.csv", shortPathEnergyScooterReturn, PathAlgorithms.calcTotalDistance(shortPathEnergyScooterReturn) / 1000, energyReturn, c, s, mexeTeAndre);
                            gCont.writePathToFile("SingleDeliveryScenarioScooterDistanceReturn.csv", shortPathDistanceScooterReturn, distanceReturn / 1000, PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathDistanceScooterReturn, c, s, mexeTeAndre), c, s, mexeTeAndre);
                            System.out.println("Scooter can traverse back to the original pharmacy from the target client. Paths exported to 'SingleDeliveryScenarioScooterEnergy/DistanceReturn.csv'");
                        }
                    }
                }
            }
        } else {
            System.out.println("Load weight exceeds Scooter's max payload.");
        }
        if (dMaxPayload >= productWeight) {

            LinkedList<Address> shortPathDistanceDroneGoing = new LinkedList<>();
            LinkedList<Address> shortPathDistanceDroneReturn = new LinkedList<>();
            LinkedList<Address> shortPathEnergyDroneGoing = new LinkedList<>();
            LinkedList<Address> shortPathEnergyDroneReturn = new LinkedList<>();

            double distanceGoing = gCont.getShortestPath(false, pharmacyAddress, clientAddress, shortPathDistanceDroneGoing);
            double distanceReturn = gCont.getShortestPath(false, clientAddress, pharmacyAddress, shortPathDistanceDroneReturn);
            gCont.getShortestPathEnergy(false, pharmacyAddress, clientAddress, shortPathEnergyDroneGoing);
            gCont.getShortestPathEnergy(false, clientAddress, pharmacyAddress, shortPathEnergyDroneReturn);
            double energyGoing = PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathEnergyDroneGoing, d, mexeTeAndre);
            double energyReturn = PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathEnergyDroneReturn, d, mexeTeAndre);

            if (shortPathDistanceDroneGoing.isEmpty()) {
                System.out.println("Drone cannot travel from the pharmacy to the target client.");

            } else {

                if (energyGoing > d.getCurrentBattery()) {
                    System.out.println("Drone does not have the energy to travel from the pharmacy to the target client.");

                } else {
                    gCont.writePathToFile("SingleDeliveryScenarioDroneEnergyGoing.csv", shortPathEnergyDroneGoing, PathAlgorithms.calcTotalDistance(shortPathEnergyDroneGoing) / 1000, energyGoing, d, mexeTeAndre);
                    gCont.writePathToFile("SingleDeliveryScenarioDroneDistanceGoing.csv", shortPathDistanceDroneGoing, distanceGoing / 1000, PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathDistanceDroneGoing, d, mexeTeAndre), d, mexeTeAndre);
                    System.out.println("Drone can traverse to the target client. Paths exported to 'SingleDeliveryScenarioDroneDistance/EnergyGoing.csv'");

                    if (shortPathDistanceDroneReturn.isEmpty()) {
                        System.out.println("Drone cannot travel back to the pharmacy from the target client.");

                    } else {

                        if (energyGoing + energyReturn > d.getCurrentBattery()) {

                            List<Address> listPharmacy = geoCont.getPharmacyAddresses();
                            Address nearestPharmacy = gCont.getNearestPharmacy(false, clientAddress, listPharmacy);

                            if (nearestPharmacy.equals(pharmacyAddress)) {
                                System.out.println("Drone does not have energy to go back to the original pharmacy nor does it have a pharmacy to charge in.");

                            } else {
                                LinkedList<Address> shortPathDistanceDroneClientToPharmacy = new LinkedList<>();
                                LinkedList<Address> shortPathEnergyDroneClientToPharmacy = new LinkedList<>();

                                double distanceToPharmacy = gCont.getShortestPath(false, clientAddress, nearestPharmacy, shortPathDistanceDroneClientToPharmacy);
                                gCont.getShortestPath(false, clientAddress, nearestPharmacy, shortPathEnergyDroneClientToPharmacy);
                                double energyToPharmacy = PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathEnergyDroneClientToPharmacy, d, mexeTeAndre);

                                if (energyGoing + energyToPharmacy > d.getCurrentBattery()) {
                                    System.out.println("Drone does not have energy to go back to the original pharmacy nor does it have energy to reach the nearest pharmacy to charge in.");

                                } else {
                                    gCont.writePathToFile("SingleDeliveryScenarioDroneEnergyToPharmacyToCharge.csv", shortPathEnergyDroneClientToPharmacy, PathAlgorithms.calcTotalDistance(shortPathEnergyDroneClientToPharmacy) / 1000, energyToPharmacy, d, mexeTeAndre);
                                    gCont.writePathToFile("SingleDeliveryScenarioDroneDistanceToPharmacyToCharge.csv", shortPathDistanceDroneClientToPharmacy, distanceToPharmacy / 1000, PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathDistanceDroneClientToPharmacy, d, mexeTeAndre), d, mexeTeAndre);
                                    System.out.println("Drone can traverse to a pharmacy in order to charge. Paths exported to 'SingleDeliveryScenarioDroneEnergy/DistanceToPharmacyToCharge.csv'");

                                    //INSERT HERE THE CHARGING SCENARIO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    LinkedList<Address> shortPathDistanceDronePharmacyToPharmacy = new LinkedList<>();
                                    LinkedList<Address> shortPathEnergyDronePharmacyToPharmacy = new LinkedList<>();

                                    double distanceToOrigPharmacy = gCont.getShortestPath(false, nearestPharmacy, pharmacyAddress, shortPathDistanceDronePharmacyToPharmacy);
                                    gCont.getShortestPath(false, clientAddress, nearestPharmacy, shortPathEnergyDronePharmacyToPharmacy);
                                    double energyToOrigPharmacy = PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathEnergyDronePharmacyToPharmacy, d, mexeTeAndre);

                                    if (energyToOrigPharmacy > d.getCurrentBattery()) {
                                        System.out.println("Drone does not have the energy to go back to the original pharmacy from the one it has charged in.");

                                    } else {
                                        gCont.writePathToFile("SingleDeliveryScenarioDroneEnergyBackToPharmacy.csv", shortPathEnergyDronePharmacyToPharmacy, PathAlgorithms.calcTotalDistance(shortPathEnergyDronePharmacyToPharmacy) / 1000, energyToOrigPharmacy, d, mexeTeAndre);
                                        gCont.writePathToFile("SingleDeliveryScenarioDroneDistanceBackToPharmacy.csv", shortPathDistanceDronePharmacyToPharmacy, distanceToOrigPharmacy / 1000, PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathDistanceDronePharmacyToPharmacy, d, mexeTeAndre), d, mexeTeAndre);
                                        System.out.println("Drone can traverse from the pharmacy it has charge in to the original pharmacy. Paths exported to 'SingleDeliveryScenarioDroneEnergy/DistancePharmacyToPharmacy.csv'");
                                    }
                                }
                            }
                        } else {
                            gCont.writePathToFile("SingleDeliveryScenarioDroneEnergyReturn.csv", shortPathEnergyDroneReturn, PathAlgorithms.calcTotalDistance(shortPathEnergyDroneReturn) / 1000, energyReturn, d, mexeTeAndre);
                            gCont.writePathToFile("SingleDeliveryScenarioDroneDistanceReturn.csv", shortPathDistanceDroneReturn, distanceReturn / 1000, PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathDistanceDroneReturn, d, mexeTeAndre), d, mexeTeAndre);
                            System.out.println("Drone can traverse back to the original pharmacy from the target client. Paths exported to 'SingleDeliveryScenarioDroneEnergy/DistanceReturn.csv'");
                        }
                    }
                }
            }
        } else {
            System.out.println("Load weight exceeds Drone's max payload.");
        }
        System.out.println("Single Delivery Scenario Ended.");
    }

    public static void scenarioMultipleDeliveries() {

        GraphController gCont = new GraphController();
        GeographicalController geoCont = new GeographicalController();
        UpdateDroneController dCont = new UpdateDroneController();
        UpdateScooterController sCont = new UpdateScooterController();
        CreateDeliveryController cdCont = new CreateDeliveryController();
        CourierDB cDB = new CourierDB();
        AddressDB aDB = new AddressDB();
        List<Address> clientAddresses = new ArrayList<>();
        clientAddresses.add(new Address("feup", 41.1775, 8.598056, 111));
        clientAddresses.add(new Address("parque de serralves", 41.159722, 8.659722, 60));

        Courier c = cDB.getCourier("courier3@gmail.com");
        Scooter s = cdCont.getHighestBatteryScooter(1);
        Drone d = cdCont.getHighestBatteryDrone(1);
        // Delete this after arranging proper data.
        List<Product> mexeTeAndre = new ArrayList<>();
        mexeTeAndre.add(new Product(1, "", 0.0, sCont.getScooterMaxPayload() / 1000, 1));
        // Above
        List<Address> la = geoCont.getAddresses();
        List<Path> lp = geoCont.getPaths(la);
        gCont.fillGraphDrone(la, lp);
        gCont.fillGraphDroneEnergy(la, lp);
        gCont.fillGraphScooter(la, lp);
        gCont.fillGraphScooterEnergy(la, lp);

        Address pharmacyAddress = aDB.getAddressPharmacyById(1);
        double sMaxPayload = sCont.getScooterMaxPayload();
        double dMaxPayload = dCont.getDroneMaxPayload();
        double productWeight = mexeTeAndre.stream().mapToDouble(Product::getWeight).sum();

        if (sMaxPayload >= productWeight) {

            LinkedList<Address> shortPathDistanceScooter = new LinkedList<>();
            LinkedList<Address> shortPathEnergyScooter = new LinkedList<>();

            double distance = gCont.getShortestPathThroughNodes(true, pharmacyAddress, pharmacyAddress, clientAddresses, shortPathDistanceScooter);
            gCont.getShortestPathThroughNodesEnergy(true, pharmacyAddress, pharmacyAddress, clientAddresses, shortPathEnergyScooter);
            double energy = PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathEnergyScooter, c, s, mexeTeAndre);

            if (shortPathDistanceScooter.isEmpty()) {
                System.out.println("Scooter cannot travel from the pharmacy to the target clients.");

            } else {

                if (energy > s.getCurrentBattery()) {
                    System.out.println("Scooter does not have the necessary energy to traverse the given path.");

                } else {
                    gCont.writePathToFile("MultipleDeliveryScenarioScooterDistance.csv", shortPathDistanceScooter, distance / 1000, PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathDistanceScooter, c, s, mexeTeAndre), c, s, mexeTeAndre);
                    gCont.writePathToFile("MultipleDeliveryScenarioScooterEnergy.csv", shortPathEnergyScooter, PathAlgorithms.calcTotalDistance(shortPathEnergyScooter) / 1000, energy, c, s, mexeTeAndre);
                    System.out.println("Scooter can traverse from the original pharmacy to the target clients and return. Paths exported to 'MultipleDeliveryScenarioScooterEnergy/Distance.csv'");
                }
            }
        } else {
            System.out.println("Load weight exceeds Scooter's max payload.");
        }
        if (dMaxPayload >= productWeight) {

            LinkedList<Address> shortPathDistanceDrone = new LinkedList<>();
            LinkedList<Address> shortPathEnergyDrone = new LinkedList<>();

            double distance = gCont.getShortestPathThroughNodes(false, pharmacyAddress, pharmacyAddress, clientAddresses, shortPathDistanceDrone);
            gCont.getShortestPathThroughNodes(false, pharmacyAddress, pharmacyAddress, clientAddresses, shortPathEnergyDrone);
            double energy = PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathEnergyDrone, d, mexeTeAndre);

            if (shortPathDistanceDrone.isEmpty()) {
                System.out.println("Drone cannot travel from the pharmacy to the target clients.");

            } else {

                if (energy > d.getCurrentBattery()) {
                    System.out.println("Drone does not have the necessary energy to traverse the given path.");

                } else {
                    gCont.writePathToFile("MultipleDeliveryScenarioDroneDistance.csv", shortPathDistanceDrone, distance / 1000, PathAlgorithms.calcDroneTotalEnergy(gCont.getGraphDroneEnergy(), shortPathDistanceDrone, d, mexeTeAndre), d, mexeTeAndre);
                    gCont.writePathToFile("MultipleDeliveryScenarioDroneEnergy.csv", shortPathEnergyDrone, PathAlgorithms.calcTotalDistance(shortPathEnergyDrone) / 1000, energy, d, mexeTeAndre);
                    System.out.println("Drone can traverse from the original pharmacy to the target clients and return. Paths exported to 'MultipleDeliveryScenarioDroneEnergy/Distance.csv'");
                }
            }
        } else {
            System.out.println("Load weight exceeds Drone's max payload.");
        }
        System.out.println("Multiple Delivery Scenario Ended.");
    }

    //A simulation of parking... yeah...
    //CHECK THE DB STUFF, IM COMPLETELY OUT OF THE LOOP ON THAT
    public static void parkingScenario() {
        //Start the File watcher. This is REQUIRED.
        AssemblyWatcher asmWatch = new AssemblyWatcher();

        Thread thr = new Thread(asmWatch);
        thr.setDaemon(true);
        thr.start();

        //Get the vehicle from db, here I just created a object. Build the controller.
        VehicleParkingController vpC = new VehicleParkingController();
        Vehicle vc = new Vehicle(1, 1, 2, 3.3, 4.4, 5.5, 36.0, 120.0, 56.5);

        //First one. Real.
        vpC.writeChargerRequest(vc, true);
        //Waiting is just for the C part to catch up, allows to see the process happen on the linux box
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Second one. Bad park.
        vpC.writeChargerRequest(vc, false);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Third one. Real.
        vpC.writeChargerRequest(vc, true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Here to make sure it doesnt die before doing all the stuff.
        System.out.println("Type anything to stop program");
        Scanner in = new Scanner(System.in);
        in.nextLine();

    }
}
