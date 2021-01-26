package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.*;
import lapr.project.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.PathAlgorithms;
import lapr.project.utils.Utils;

class Main {

    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //load database properties

        SettingsHandler sH = new SettingsHandler();
        sH.loadSettings(SettingsHandler.SETTINGS_FILE);

        System.out.println(System.getProperties());
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);

        setUpProperties();
//        scenarioPurchaseOrderNoCredits();
//        scenarioPurchaseOrderEnoughCreditsBackOrder();
//        scenarioPurchaseOrderOverMaxWeight();
//        scenarioOneDelivery();
//        scenarioMultipleDeliveries();
//        scenarioQrCode();
//        parkingScenario();
//        testScenarioLand7_1();

        //Initial Database Setup
        //DataHandler dh = new DataHandler();
        //dh.scriptRunner("Documentation/BDDAD/LAPR3_DATABASE_CREATION.sql");
        //dh.scriptRunner("Documentation/BDDAD/LAPR3_INSERTS.sql");
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

    public static void scenarioPurchaseOrderNoCredits() {
        System.out.println("filling graph");
        GeographicalController geographicalController = new GeographicalController();
        List<Address> addressList = geographicalController.getAddresses();
        List<Path> pathList = geographicalController.getPaths(addressList);
        GraphController graphController = new GraphController();
        graphController.fillGraphScooterEnergy(addressList, pathList);

        PurchaseItemsController purchaseItemsController = new PurchaseItemsController();
        Product product1 = new Product(1, "Ben-u-ron", 2.40, 0.1, 1);
        Product product2 = new Product(2, "Ibuprofeno", 4.70, 0.2, 1);
        System.out.println("Adding products to cart");
        int idOrder = 1;
        System.out.println("Adding product: " + product1.toString() + " Quantity: 3");
        System.out.println(purchaseItemsController.addToBasket(product1, 3));
        System.out.println("Adding product: " + product2.toString() + " Quantity: 6");
        System.out.println(purchaseItemsController.addToBasket(product2, 2));
        System.out.println(purchaseItemsController.addToBasket(product2, 4));
        PurchaseOrder purchaseOrder1 = purchaseItemsController.purchaseItems(idOrder, 1, "client1@gmail.com", graphController.getGraphScooterEnergy());
        System.out.println(purchaseOrder1);
        if (purchaseOrder1 == null) {
            System.out.println("Error during purchase.");
            return;
        }

        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        System.out.println("Creating Invoice");
        System.out.println("Updating Credits.");
        Invoice invoice = createInvoiceController.createInvoice(idOrder, purchaseOrder1);
        System.out.println("Sending Invoice By Email");
        createInvoiceController.sendInvoiceByEmail(invoice);

    }

    public static void scenarioPurchaseOrderEnoughCreditsBackOrder() {
        System.out.println("filling graph");
        GeographicalController geographicalController = new GeographicalController();
        List<Address> addressList = geographicalController.getAddresses();
        List<Path> pathList = geographicalController.getPaths(addressList);
        GraphController graphController = new GraphController();
        graphController.fillGraphScooterEnergy(addressList, pathList);

        PurchaseItemsController purchaseItemsController = new PurchaseItemsController();
        Product product1 = new Product(1, "Ben-u-ron", 2.40, 0.1, 1);
        System.out.println("Adding products to cart");
        int idOrder = 2;
        System.out.println("Adding product: " + product1.toString() + " Quantity: 10");
        System.out.println(purchaseItemsController.addToBasket(product1, 10));
        PurchaseOrder purchaseOrder2 = purchaseItemsController.purchaseItems(idOrder, 1, "client1@gmail.com", graphController.getGraphScooterEnergy());
        System.out.println(purchaseOrder2);
        if (purchaseOrder2 == null) {
            System.out.println("Error during purchase.");
            return;
        }

        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        System.out.println("Creating Invoice");
        System.out.println("Updating Credits.");
        Invoice invoice = createInvoiceController.createInvoice(idOrder, purchaseOrder2);
        System.out.println("Sending Invoice By Email");
        createInvoiceController.sendInvoiceByEmail(invoice);

        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        purchaseOrderList.add(purchaseOrder2);
        CreateDeliveryController createDeliveryController = new CreateDeliveryController();
        System.out.println("Creating delivery Run 1");
        createDeliveryController.createDeliveries(purchaseOrderList, 1);
    }

    public static void scenarioPurchaseOrderOverMaxWeight() {
        System.out.println("filling graph");
        GeographicalController geographicalController = new GeographicalController();
        List<Address> addressList = geographicalController.getAddresses();
        List<Path> pathList = geographicalController.getPaths(addressList);
        GraphController graphController = new GraphController();
        graphController.fillGraphScooterEnergy(addressList, pathList);

        PurchaseItemsController purchaseItemsController = new PurchaseItemsController();
        Product product5 = new Product(1, "Escova de Dentes Deluxe Edition", 15.99, 0.80, 2);
        System.out.println("Adding products to cart");
        int idOrder = 3;
        System.out.println("Adding product: " + product5.toString() + " Quantity: 10");
        System.out.println(purchaseItemsController.addToBasket(product5, 10));
        PurchaseOrder purchaseOrder2 = purchaseItemsController.purchaseItems(idOrder, 1, "client1@gmail.com", graphController.getGraphScooterEnergy());
        System.out.println(purchaseOrder2);
        if (purchaseOrder2 == null) {
            System.out.println("Basket over max weight. Order cancelled.");
            return;
        }

        System.out.println("Updating Credits.");
        CreateInvoiceController createInvoiceController = new CreateInvoiceController();
        System.out.println("Creating Invoice");
        Invoice invoice = createInvoiceController.createInvoice(idOrder, purchaseOrder2);
        System.out.println("Sending Invoice By Email");
        createInvoiceController.sendInvoiceByEmail(invoice);
    }

    //We decide the address to visit and the pharmacy we go from. Serves as template for multiple scenarios, just have to adjust values to trigger them.
    public static void scenarioOneDelivery() {

        GraphController gCont = new GraphController();
        GeographicalController geoCont = new GeographicalController();
        UpdateDroneController dCont = new UpdateDroneController();
        UpdateScooterController sCont = new UpdateScooterController();
        CreateDeliveryController cdCont = new CreateDeliveryController();
        NotifyClientController notifyClientController = new NotifyClientController();
        CourierDB cDB = new CourierDB();
        ClientDB clientDB = new ClientDB();
        AddressDB aDB = new AddressDB();

        int deliveryRun = 1;

        List<PurchaseOrder> purchaseOrderList = cdCont.getPurchaseOrdersFromDeliveryRun(deliveryRun);
        List<ProductLine> productLineList = cdCont.getProductLinesFromDeliveryRun(purchaseOrderList);
        List<Product> mexeTeAndre = cdCont.getProductsFromDeliveryRun(productLineList);

        Client client = clientDB.getClientByEmail(purchaseOrderList.get(0).getClientEmail());
        Address clientAddress = client.getAddress();
        Courier c = cDB.getCourier("courier1@gmail.com");
        Scooter s = cdCont.getHighestBatteryScooter(1);
        Drone d = cdCont.getHighestBatteryDrone(1);

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

                                    System.out.println("Charging Scooter at Pharmacy at Address : " + nearestPharmacy.getDescription());
                                    LinkedList<Address> shortPathDistanceScooterPharmacyToPharmacy = new LinkedList<>();
                                    LinkedList<Address> shortPathEnergyScooterPharmacyToPharmacy = new LinkedList<>();

                                    double distanceToOrigPharmacy = gCont.getShortestPath(true, nearestPharmacy, pharmacyAddress, shortPathDistanceScooterPharmacyToPharmacy);
                                    gCont.getShortestPath(true, nearestPharmacy, pharmacyAddress, shortPathEnergyScooterPharmacyToPharmacy);
                                    double energyToOrigPharmacy = PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathEnergyScooterPharmacyToPharmacy, c, s, mexeTeAndre);

                                    if (energyToOrigPharmacy > s.getCurrentBattery()) {
                                        System.out.println("Scooter does not have the energy to go back to the original pharmacy from the one it has charged in.");

                                    } else {
                                        gCont.writePathToFile("SingleDeliveryScenarioScooterEnergyBackToPharmacy.csv", shortPathEnergyScooterPharmacyToPharmacy, PathAlgorithms.calcTotalDistance(shortPathEnergyScooterPharmacyToPharmacy) / 1000, energyToOrigPharmacy, c, s, mexeTeAndre);
                                        gCont.writePathToFile("SingleDeliveryScenarioScooterDistanceBackToPharmacy.csv", shortPathDistanceScooterPharmacyToPharmacy, distanceToOrigPharmacy / 1000, PathAlgorithms.calcScooterTotalEnergy(gCont.getGraphScooterEnergy(), shortPathDistanceScooterPharmacyToPharmacy, c, s, mexeTeAndre), c, s, mexeTeAndre);
                                        System.out.println("Scooter can traverse from the pharmacy it has charge in to the original pharmacy. Paths exported to 'SingleDeliveryScenarioScooterEnergy/DistanceBackToPharmacy.csv'");
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

                                    System.out.println("Charging Drone at Pharmacy at Address : " + nearestPharmacy.getDescription());
                                    LinkedList<Address> shortPathDistanceDronePharmacyToPharmacy = new LinkedList<>();
                                    LinkedList<Address> shortPathEnergyDronePharmacyToPharmacy = new LinkedList<>();

                                    double distanceToOrigPharmacy = gCont.getShortestPath(false, nearestPharmacy, pharmacyAddress, shortPathDistanceDronePharmacyToPharmacy);
                                    gCont.getShortestPath(false, nearestPharmacy, pharmacyAddress, shortPathEnergyDronePharmacyToPharmacy);
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

        System.out.println("Associating drone to delivery run");
        cdCont.deliveryRunAssociateVehicle(d.getIdVehicle(), deliveryRun);
        System.out.println("Notifying client");
        notifyClientController.notifyClientDeliveryRunStarts(purchaseOrderList.get(0));
        System.out.println("Single Delivery Scenario Ended.");
    }

    public static void scenarioMultipleDeliveries() {

        GraphController gCont = new GraphController();
        GeographicalController geoCont = new GeographicalController();
        UpdateDroneController dCont = new UpdateDroneController();
        UpdateScooterController sCont = new UpdateScooterController();
        CreateDeliveryController cdCont = new CreateDeliveryController();
        NotifyClientController notifyClientController = new NotifyClientController();
        CourierDB cDB = new CourierDB();
        AddressDB aDB = new AddressDB();
        ClientDB clientDB = new ClientDB();

        int deliveryRun = 2;

        List<PurchaseOrder> purchaseOrderList = cdCont.getPurchaseOrdersFromDeliveryRun(deliveryRun);
        List<ProductLine> productLineList = cdCont.getProductLinesFromDeliveryRun(purchaseOrderList);
        List<Product> mexeTeAndre = cdCont.getProductsFromDeliveryRun(productLineList);

        List<Address> clientAddresses = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
            Client client = clientDB.getClientByEmail(purchaseOrder.getClientEmail());
            Address clientAddress = client.getAddress();
            clientAddresses.add(clientAddress);
        }

        Courier c = cDB.getCourier("courier3@gmail.com");
        Scooter s = cdCont.getHighestBatteryScooter(1);
        Drone d = cdCont.getHighestBatteryDrone(1);

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

        System.out.println("Associating scooter to delivery run");
        cdCont.deliveryRunAssociateVehicle(s.getIdVehicle(), deliveryRun);

        System.out.println("Associating courier to delivery run");
        cdCont.deliveryRunAssociateCourier(c.getEmail(), deliveryRun);

        System.out.println("Notifying client");
        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
            notifyClientController.notifyClientDeliveryRunStarts(purchaseOrder);
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
        Vehicle vc = new Vehicle(101, 1, 2, 3.3, 4.4, 5.5, 36.0, 120.0, 56.5);

        //First one. Real.
        vpC.writeChargerRequest(vc, true);
        //Waiting is just for the C part to catch up, allows to see the process happen on the linux box
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        vc = new Vehicle(102, 1, 2, 3.3, 4.4, 5.5, 36.0, 120.0, 56.5);

        //Second one. Bad park.
        vpC.writeChargerRequest(vc, false);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Third one. Real.
        vpC.writeChargerRequest(vc, true);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Here to make sure it doesnt die before doing all the stuff.
        System.out.println("Type anything to stop program");
        Scanner in = new Scanner(System.in);
        in.nextLine();

    }

    public static void scenarioQrCode() {
        AddScooterController addScooterController = new AddScooterController();
        AddDroneController addDroneController = new AddDroneController();

        Scooter scooter = new Scooter(11, 2, 16, 1.1, 1.5, 1, 500, 500, 8.9, 1);
        Drone drone = new Drone(12, 1, 4.5, 0.65, 0.47, 1, 500, 500, 21.9, 0.5, 7, 1);

        System.out.println("Adding Scooter: id:" + scooter.getIdVehicle());
        System.out.println(addScooterController.addScooter(scooter));
        System.out.println("Adding Drone: id:" + drone.getIdVehicle());
        System.out.println(addDroneController.addDrone(drone));
    }

    public static void testScenarioLand7_1() {

        double totalEnergy = 0;

        Scooter s = new Scooter(1, 1, 50, 1.1, 0.3, 0, 3000, 3000, 8.33, 1);
        Courier c = new Courier("", "", "", 1, 1L, 1, 80);
        List<Product> lpro = new ArrayList<>();
        StringBuilder q = new StringBuilder();

        q.append("Test Scenario Land 7.1.\n");
        q.append("Scooter Weight = 50kg.\n");
        q.append("Courier Weight = 80kg.\n");
        q.append("Scooter Average Speed = 30km/h.\n");
        q.append("Scooter Frontal Area = 0.3m2.\n");
        q.append("Aerodynamic Coefficient = 1.1 (unitless).\n");
        q.append("\n");

        Address x = new Address("Aqui", 12, 12, 100);
        Address y = new Address("Ali", 12, 12.0919, 103);
        Address z = new Address("Além", 12, 12.0459, 103);

        Path p1 = new Path(x, y, 0.5, 20, 3.06);
        Path p2 = new Path(y, z, 0.5, 20, 3.06);

        q.append(x + " -> " + y + " -> " + z + "\n");
        q.append("As this is an encapsulated test scenario, the following values remain true for the entirety of the course.\n");
        q.append("Road Rolling Resistance Coefficient = 0.5 (unitless).\n");
        q.append("Wind Angle = 20 Degrees.\n");
        q.append("Wind Speed = 11km/h.\n");

        totalEnergy += PathAlgorithms.calcScooterEnergy(p1, c, s, lpro);
        totalEnergy += PathAlgorithms.calcScooterEnergy(p2, c, s, lpro);

        if (totalEnergy <= s.getMaxBattery()) {
            q.append("The Scooter can perform the traversal needed.\n");

        } else {
            q.append("The Scooter cannot perform the traversal needed.\n");
        }
        q.append("Energy Required = " + String.format("%.2f", totalEnergy) + "Wh.\n");
        q.append("Available Energy = " + String.format("%.2f", s.getMaxBattery()) + "Wh.\n");

        System.out.println("Data exported to 'TestScenarioLand7.1.txt'");
        Utils.writeFile(q.toString(), "TestScenarioLand7.1.txt");
    }
}
