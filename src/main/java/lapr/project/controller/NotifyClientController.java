package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * Controls all interactions related to notifying the client.
 *
 * @author lapr3-2020-g052
 */
public class NotifyClientController {

    /**
     * StockDB instance for obtaining the quantities of product at each pharmacy stock.
     */
    private final StockDB stockDB;

    /**
     * DeliveryDB instance for obtaining the client email from an order.
     */
    private final DeliveryDB deliveryDB;

    /**
     * DeliveryStatusDB instance for obtaining updating the status of q delivery after it starts.
     */
    private final DeliveryStatusDB deliveryStatusDB;

    /**
     * ClientDB instance for obtaining the client that purchased the order.
     */
    private final ClientDB clientDB;

    /**
     * PharmacyDB instance for obtaining the pharmacys involved in a delivery.
     */
    private final PharmacyDB pharmacyDB;

    /**
     * EmailService instance for obtaining to send an email to the client notifying him.
     */
    private final EmailService emailService;

    /**
     * AddressDB instance for obtaining the pharmacys addresses.
     */
    private final AddressDB addressDB;

    /**
     * Creates a instance of NotifyClientController, creating the required instances.
     */
    public NotifyClientController() {

        stockDB = new StockDB();
        deliveryDB = new DeliveryDB();
        deliveryStatusDB = new DeliveryStatusDB();
        clientDB = new ClientDB();
        pharmacyDB = new PharmacyDB();
        emailService = new EmailService();
        addressDB = new AddressDB();
    }

    /**
     * Creates a instance of NotifyClientController with the given instances.
     * @param stockDB stockDB instance.
     * @param deliveryDB deliveryDB instance.
     * @param deliveryStatusDB deliveryStatusDB instance.
     * @param clientDB clientDB instance.
     * @param pharmacyDB pharmacyDB instance.
     * @param emailService emailService instance.
     * @param addressDB addressDB instance.
     */
    public NotifyClientController(StockDB stockDB, DeliveryDB deliveryDB, DeliveryStatusDB deliveryStatusDB,
            ClientDB clientDB, PharmacyDB pharmacyDB, EmailService emailService, AddressDB addressDB) {

        this.stockDB = stockDB;
        this.deliveryDB = deliveryDB;
        this.deliveryStatusDB = deliveryStatusDB;
        this.clientDB = clientDB;
        this.pharmacyDB = pharmacyDB;
        this.emailService = emailService;
        this.addressDB = addressDB;
    }

    /**
     * Checks if the pharmacy has enough stock for the delivery or if is necessary to back order from another pharmacy
     * @param pharmacy pharmacy
     * @param product product
     * @param productQuantity  product quantity
     * @param graph graph
     * @return true if the pharmacy or eith other pharmacy helps the necessary stock, false otherwise
     */
    public Boolean checkForStock(Pharmacy pharmacy, Product product, int productQuantity, Graph<Address, Path> graph) {

        int missingProduct = checkIfIsEnoughStock(pharmacy, product, productQuantity);
        if (missingProduct == 0) return true;

        return checkIfIsEnoughStockOthersPharmacy(pharmacy, product, missingProduct, graph);
    }

    /**
     * Verifies if the pharmacy requested by the client has enought stock
     * @param pharmacy pharmacy
     * @param product product
     * @param productQuantity product quantity
     * @return 0 if it has enought stock, a number higher than 0 that represents the product quantity missing in the pharmacy
     */
    public int checkIfIsEnoughStock(Pharmacy pharmacy, Product product, int productQuantity) {

        return stockDB.checkIfIsEnoughStock(pharmacy.getId(), product.getId(), productQuantity);
    }

    /**
     * Verifies if the others pharmacys has the missing stock related to the delivery
     * @param pharmacy pharmacy
     * @param product product
     * @param productQuantity product quantity
     * @param graph graph
     * @return true if another pharmacy has enough stock or false otherwise
     */
    //the graph received as parameter must be graphScooterEnergy
    public Boolean checkIfIsEnoughStockOthersPharmacy(Pharmacy pharmacy, Product product, int productQuantity,
            Graph<Address, Path> graph) {

        List<Address> addressList = stockDB.getOthersPharmacyAddressWithProductStock(pharmacy.getId(), product.getId(), productQuantity);

        if (addressList.isEmpty()) {
            return false;
        }
        Address addressPharmacy = addressDB.getAddressPharmacyById(pharmacy.getId());
        Address address = GraphAlgorithms.getNearestPharmacy(true, graph, addressPharmacy, addressList);
        Pharmacy pharmacy2 = pharmacyDB.getPhamacyByAddress(address);

        LinkedList<Address> llGoing = new LinkedList<>();
        LinkedList<Address> llReturn = new LinkedList<>();

        double energyGoing = GraphAlgorithms.shortestPath(graph, addressPharmacy, address, llGoing);
        double energyReturn = GraphAlgorithms.shortestPath(graph, addressPharmacy, address, llReturn);

        if (!llGoing.isEmpty() && !llReturn.isEmpty() && (energyGoing + energyReturn <= 600)) {
            return backOrder(pharmacy, pharmacy2, product, productQuantity);
        }
        return false;
    }

    /**
     * Back orders the product from another pharmacy
     * @param pharmacy1 one pharmacy
     * @param pharmacy2 another pharmacy
     * @param product product
     * @param productQuantity product quantity
     * @return true if it back orders or false otherwise
     */
    public boolean backOrder(Pharmacy pharmacy1, Pharmacy pharmacy2, Product product, int productQuantity) {
        return stockDB.backOrder(pharmacy1.getId(), pharmacy2.getId(), product.getId(), productQuantity);
    }

    /**
     * Notifies the client that his delivery run had started
     * @param order order
     * @return true if the email has been sent or false otherwise
     */
    public boolean notifyClientDeliveryRunStarts(PurchaseOrder order) {

        String subjectLine = "Delivery Run Starts";
        String clientEmail = deliveryDB.getClientEmailFromOrder(order.getId());
        Client client = clientDB.getClientByEmail(clientEmail);

        String emailBody = "Dear " + client.getName() + ". This email is just to let you know that the delivery is on the way";

        if (deliveryStatusDB.updateDeliveryStatusInDelivery(order.getId())) {
            return emailService.sendEmail("clientemen0652@gmail.com", subjectLine, emailBody);
        }
        return false;
    }
}
