package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.PurchaseOrder;

import java.sql.SQLException;

public class NotifieClientController {

    private final StockDB stockDB;

    public NotifieClientController() { stockDB = new StockDB();
    }

    public NotifieClientController(StockDB stockDB) { this.stockDB = stockDB;
    }

     //se retornar true é porque ainda tem stock
     public Boolean checkIfIsEnoughStock(PurchaseOrder order)throws SQLException {

        if(stockDB.checkIfIsEnoughStock(order.getId()) || stockDB.checkIfIsEnoughStockInOtherPharmacy(order.getId())){
            return true;
        }else {
            return false;
        }
     }
}
