package lapr.project.controller;

import com.google.zxing.WriterException;
import lapr.project.data.ScooterDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Scooter;

import java.io.IOException;

public class AddScooterController {

    private final ScooterDB scooterDB;
    private final ParkDB parkDB;

    public AddScooterController() {

        scooterDB = new ScooterDB();
        parkDB = new ParkDB();
    }

    public AddScooterController(ScooterDB scooterDB, ParkDB parkDB) {

        this.scooterDB = scooterDB;
        this.parkDB = parkDB;
    }

    public void qrController(Scooter s) throws IOException, WriterException {
        scooterDB.generateQRCodeImage(s.toString(),200,200,"./qrScooter"+s.getIdVehicle()+".png");
    }
    
    public boolean addScooter(Scooter scooter) {

        if (parkDB.getNumberOfScootersInPharmacy(scooter.getIdPharmacy()) < parkDB.getLimitVehiclesPark(scooter.getIdPharmacy(), "scooter")) {
            return scooterDB.addScooter(scooter);
        }
        return false;
    }
}
