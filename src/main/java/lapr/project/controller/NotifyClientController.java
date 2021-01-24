package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class NotifyClientController {

    private final StockDB stockDB;
    private final DeliveryDB deliveryDB;
    private final DeliveryStatusDB deliveryStatusDB;
    private final ClientDB clientDB;
    private final EmailService emailService;
    private final AddressDB addressDB;

    public NotifyClientController() {
        stockDB = new StockDB();
        deliveryDB = new DeliveryDB();
        deliveryStatusDB = new DeliveryStatusDB();
        clientDB = new ClientDB();
        emailService = new EmailService();
        addressDB = new AddressDB();
    }

    public NotifyClientController(StockDB stockDB, DeliveryDB deliveryDB, DeliveryStatusDB deliveryStatusDB, ClientDB clientDB, EmailService emailService, AddressDB addressDB) {
        this.stockDB = stockDB;
        this.deliveryDB = deliveryDB;
        this.deliveryStatusDB = deliveryStatusDB;
        this.clientDB = clientDB;
        this.emailService = emailService;
        this.addressDB = addressDB;
    }

    public Boolean checkForStock(Pharmacy pharmacy, Product product, int productQuantity, Graph<Address, Path> graph)throws SQLException {

        if(checkIfIsEnoughStock(pharmacy, product, productQuantity) || checkIfIsEnoughStockOthersPharmacy(pharmacy, product, productQuantity, graph)){
            return true;
        }
        return false;
    }

     //se retornar true é porque ainda tem stock
     public Boolean checkIfIsEnoughStock(Pharmacy pharmacy, Product product, int productQuantity)throws SQLException {

        return stockDB.checkIfIsEnoughStock(pharmacy.getId(), product.getId(), productQuantity);
     }

     //the graph received as parameter must be graphScooterEnergy
     public Boolean checkIfIsEnoughStockOthersPharmacy(Pharmacy pharmacy, Product product, int productQuantity, Graph<Address, Path> graph)throws SQLException {

        List<Address> addressList = stockDB.getOthersPharmacyAddressWithProductStock(pharmacy.getId(), product.getId(), productQuantity);

        if(addressList.isEmpty()){
            return false;
        }

        Address addressPharmacy = addressDB.getAddressPharmacyById(pharmacy.getId());

        Address address = GraphAlgorithms.getNearestPharmacy(true, graph, addressPharmacy, addressList);

        LinkedList<Address> llGoing = new LinkedList<>();
        LinkedList<Address> llReturn = new LinkedList<>();

        double energyGoing = GraphAlgorithms.shortestPath(graph, addressPharmacy, address, llGoing);
        double energyReturn = GraphAlgorithms.shortestPath(graph, addressPharmacy, address, llReturn);

        if(llGoing.size() != 0 && llReturn.size() != 0){
            return energyGoing + energyReturn <= 700;
        }

        return false;
     }

     public boolean notifyClientDeliveryRunStarts(PurchaseOrder order) throws SQLException {
         String subjectLine = "Delivery Run Starts";

         String clientEmail = deliveryDB.getClientEmailFromOrder(order.getId());
         Client client = clientDB.getClientByEmail(clientEmail);

         String emailBody = "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way";

         if(deliveryStatusDB.updateDeliveryStatusInDelivery(order.getId())){
           return emailService.sendEmail("clientemen0652@gmail.com", subjectLine, emailBody);
        }
        return false;
     }
}
