package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.DeliveryDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Courier;
import lapr.project.model.Delivery;
import lapr.project.model.PurchaseOrder;
import lapr.project.model.Scooter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignOrderToCourierScooterController {

    private final DeliveryDB dDB;
    private final CourierDB cDB;
    private final ScooterDB sDB;
    private static int deleveryRun = 0;

    public AssignOrderToCourierScooterController() {
        dDB = new DeliveryDB();
        cDB = new CourierDB();
        sDB = new ScooterDB();
    }

    public AssignOrderToCourierScooterController(DeliveryDB dDB, CourierDB cDB, ScooterDB sDB) {
        this.cDB = cDB;
        this.sDB = sDB;
        this.dDB = dDB;
    }

    public List<Courier> getAllAvailableCouriers(PurchaseOrder order) {
        return cDB.getAllAvailableCouriers(order.getId());
    }

    public List<Scooter> getAllAvailableScooters(PurchaseOrder order) {
        return sDB.getAllAvailableScooters(order.getId());
    }

    public boolean addDelivery(List<PurchaseOrder> orderList) throws SQLException {
        deleveryRun++;
        String chosenCourier;
        int chosenScooter;
        int idDeliveryStatus = 1;
        List<Courier> listCouriers = getAllAvailableCouriers(orderList.get(0));
        List<Scooter> listScooters = getAllAvailableScooters(orderList.get(0));
        ArrayList<Delivery> deliveries = new ArrayList<>();

        if(listCouriers.isEmpty()) {
            chosenCourier = null;
            idDeliveryStatus = 2;
        }
        else {
            chosenCourier = listCouriers.get(0).getEmail();
        }

        if(listScooters.isEmpty()) {
            chosenScooter = 0;
            idDeliveryStatus = 2;
        }
        else {
            chosenScooter = listScooters.get(0).getIdVehicle();
        }

        for(PurchaseOrder order : orderList) {
            deliveries.add(order.getId(), chosenScooter, chosenCourier, idDeliveryStatus, startDate, endDate, deleveryRun);
        }
        return true;
    }

}
