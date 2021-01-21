package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryStatusDB;
import lapr.project.data.EmailService;
import lapr.project.data.StockDB;
import lapr.project.model.PurchaseOrder;

import java.sql.SQLException;

public class NotifyClientController {

    private final StockDB stockDB;
    private final DeliveryDB deliveryDB;
    private final DeliveryStatusDB deliveryStatusDB;
    private final EmailService emailService;

    public NotifyClientController() {
        stockDB = new StockDB();
        deliveryDB = new DeliveryDB();
        deliveryStatusDB = new DeliveryStatusDB();
        emailService = new EmailService();
    }

    public NotifyClientController(StockDB stockDB, DeliveryDB deliveryDB, DeliveryStatusDB deliveryStatusDB, EmailService emailService) {
        this.stockDB = stockDB;
        this.deliveryDB = deliveryDB;
        this.deliveryStatusDB = deliveryStatusDB;
        this.emailService = emailService;
    }

     //se retornar true é porque ainda tem stock
     public Boolean checkIfIsEnoughStock(PurchaseOrder order)throws SQLException {

        return stockDB.checkIfIsEnoughStock(order.getId()) || stockDB.checkIfIsEnoughStockInOtherPharmacy(order.getId());
     }

     public boolean notifyClientDeliveryRunStarts(PurchaseOrder order) throws SQLException {
         String subjectLine = "Delivery Run Starts";
         String emailBody = "This email is just to let you know that the delivery is on the way";

        if(deliveryStatusDB.updateDeliveryStatusInDelivery(order.getId())){
           return emailService.sendEmail(deliveryDB.getClientEmailFromOrder(order.getId()), subjectLine, emailBody);
        }
        return false;
     }
}
