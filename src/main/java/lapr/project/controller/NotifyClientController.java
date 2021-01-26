package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

import java.util.LinkedList;
import java.util.List;

public class NotifyClientController {

    private final StockDB stockDB;
    private final DeliveryDB deliveryDB;
    private final DeliveryStatusDB deliveryStatusDB;
    private final ClientDB clientDB;
    private final PharmacyDB pharmacyDB;
    private final EmailService emailService;
    private final AddressDB addressDB;

    public NotifyClientController() {

        stockDB = new StockDB();
        deliveryDB = new DeliveryDB();
        deliveryStatusDB = new DeliveryStatusDB();
        clientDB = new ClientDB();
        pharmacyDB = new PharmacyDB();
        emailService = new EmailService();
        addressDB = new AddressDB();
    }

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

    public Boolean checkForStock(Pharmacy pharmacy, Product product, int productQuantity,
            Graph<Address, Path> graph) {

        return (checkIfIsEnoughStock(pharmacy, product, productQuantity) == 0 || checkIfIsEnoughStockOthersPharmacy(pharmacy, product, productQuantity, graph));
    }

    //se retornar 0 é porque ainda tem stock, qualquer valor maior que 0 representa a quantidade de produto em falta na farmácia
    public int checkIfIsEnoughStock(Pharmacy pharmacy, Product product, int productQuantity) {

        return stockDB.checkIfIsEnoughStock(pharmacy.getId(), product.getId(), productQuantity);
    }

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

    public boolean backOrder(Pharmacy pharmacy1, Pharmacy pharmacy2, Product product, int productQuantity) {
        return stockDB.backOrder(pharmacy1.getId(), pharmacy2.getId(), product.getId(), productQuantity);
    }

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
