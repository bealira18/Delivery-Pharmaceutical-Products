package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Client;
import lapr.project.model.PurchaseOrder;

import java.sql.SQLException;

public class NotifyClientController {

    private final StockDB stockDB;
    private final DeliveryDB deliveryDB;
    private final DeliveryStatusDB deliveryStatusDB;
    private final ClientDB clientDB;
    private final EmailService emailService;

    public NotifyClientController() {
        stockDB = new StockDB();
        deliveryDB = new DeliveryDB();
        deliveryStatusDB = new DeliveryStatusDB();
        clientDB = new ClientDB();
        emailService = new EmailService();
    }

    public NotifyClientController(StockDB stockDB, DeliveryDB deliveryDB, DeliveryStatusDB deliveryStatusDB, ClientDB clientDB, EmailService emailService) {
        this.stockDB = stockDB;
        this.deliveryDB = deliveryDB;
        this.deliveryStatusDB = deliveryStatusDB;
        this.clientDB = clientDB;
        this.emailService = emailService;
    }

     //se retornar true é porque ainda tem stock
     public Boolean checkIfIsEnoughStock(PurchaseOrder order)throws SQLException {

        return stockDB.checkIfIsEnoughStock(order.getId()) || stockDB.checkIfIsEnoughStockInOtherPharmacy(order.getId());
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
