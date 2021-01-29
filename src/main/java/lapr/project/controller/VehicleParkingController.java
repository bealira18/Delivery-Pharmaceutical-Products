package lapr.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import lapr.project.data.EmailService;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;

/**
 * VehicleParkingController handles and processes most of the interaction with 
 * the parking system of a pharmacy. This class is mostly for simulation purposes
 * as we have no on-site deployments.
 * @author lapr3-2020-g052
 */
public class VehicleParkingController {

    /**
     * Vehicle Database Instance.
     */
    public final VehicleDB vDB;

    /**
     * Email Service instance.
     */
    public final EmailService eS;

    /**
     * Creates a object of the Vehicle Parking Controller class.
     * @param vDB Vehicle Database Instance.
     * @param eS Mail Service Instance.
     */
    public VehicleParkingController(VehicleDB vDB, EmailService eS) {

        this.vDB = vDB;
        this.eS = eS;
    }

    /**
     * Creates the Vehicle of the vehicle Parking Controller class, creating the
     * needed instances to function.
     */
    public VehicleParkingController() {

        this.vDB = new VehicleDB();
        this.eS = new EmailService();
    }

    /**
     * Processes the information of a file incoming from a parking system in C
     * and analyses it's content and notifies the proper person of it's status
     * @param fileName File name that contains the pertinent information.
     * @throws FileNotFoundException
     */
    public void interpretChargerInfo(String fileName) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(System.getProperty("charger.comm.dir") + fileName));
        String buffer = sc.nextLine();
        String[] arrBuffer = buffer.split(";");

        if (arrBuffer.length != 3) {
            throw new IllegalArgumentException("Unexpected number of arguments");
        }

        sc.close();

        //Check if the vehicle ID is real and what type it is
        String vehicleType = vDB.typeOfVehicleByID(Integer.parseInt(arrBuffer[1]));
        List<String> nameNemail = vDB.getEmailNameFromParkedVehicleResponsible(Integer.parseInt(arrBuffer[1]));
        LocalDateTime date;

        date = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(arrBuffer[0])), ZoneId.of("Z"));

        eS.sendEmail(nameNemail.get(0), "Notificação de estacionamento", buildStatusEmail(nameNemail.get(1), date,
                Integer.parseInt(arrBuffer[1]), vehicleType, Float.parseFloat(arrBuffer[2])).toString());

    }

    /**
     * A class that simulates the parking of a vehicle, generating a file to be
     * handled by the parking system.
     * @param vehicle Vehicle Object, containing the information needed to pass to the parking system.
     * @param isReal Whether or not the request is supposed to be a well parked vehicle or not.
     */
    public void writeChargerRequest(Vehicle vehicle, boolean isReal) {

        LocalDateTime date = LocalDateTime.now();
        long timestamp = date.toEpochSecond(ZoneOffset.UTC);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("'lock_'yyyy'_'MM'_'dd'_'HH'_'mm'_'ss'.data'");
        String fileName = System.getProperty("charger.comm.dir") + date.format(dtf);

        String buffer = writeChargerInfo(vehicle, timestamp, isReal);

        Utils.writeFile(buffer, fileName);
        //Ordinarily, the file would go empty, but this function requires content on it, so a "flag" string placeholder is sent.
        Utils.writeFile("flag", fileName + ".flag");
    }

    /**
     * Static Charger Information String Template.
     * @param vehicle Vehicle Object, containing the vehicle information
     * @param timestamp Epoch Timestamp for time purposes
     * @param isReal Wether this request is meant to be a well parked vehicle or not.
     * 
     * @return Formatted String ready to be written to file.
     */
    public static String writeChargerInfo(Vehicle vehicle, long timestamp, boolean isReal) {

        String buffer = String.format(Locale.ROOT, "%d;%d", timestamp, vehicle.getIdVehicle());

        if (isReal) {
            //Yes, I'm aware I'm writing longs to a system that expects ints: timestamp wont be a problem until 2038, the others will never crack the integer limit.
            buffer = String.format(Locale.ROOT, "%s;%d;%d", buffer, Math.round(vehicle.getMaxBattery()), Math.round(vehicle.getCurrentBattery()));
        }
        return buffer;
    }

    /**
     * Static Status Email Template.
     * @param name Name of the person who's receiving the email
     * @param date Date that the parking took place at.
     * @param vehicleID Vehicle unique ID.
     * @param vehicleType Type of vehicle.
     * @param timeToFull Time needed (in hours) until the vehicle reaches maximum charge.
     * 
     * @return StringBuilder Object with the proper information for the body of the email.
     */
    public static StringBuilder buildStatusEmail(String name, LocalDateTime date, int vehicleID, String vehicleType,
            float timeToFull) {

        StringBuilder buildStatus = new StringBuilder("Caro Sr/a ");
        buildStatus.append(name).append(",");
        buildStatus.append(System.getProperty(Constants.LINE_BREAK));
        buildStatus.append(System.getProperty(Constants.LINE_BREAK));
        buildStatus.append("O veículo do tipo ").append(vehicleType).append(" de ID ").append(vehicleID).append(" estacionado às ");
        buildStatus.append(date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        if (timeToFull >= 0.0) {
            buildStatus.append(" foi propriamente estacionado.").append(System.getProperty(Constants.LINE_BREAK));
            buildStatus.append(String.format(Locale.ROOT, "A bateria desta estará carregada em %.2f horas.", timeToFull));
        } else {
            buildStatus.append(" foi mal estacionado.").append(System.getProperty(Constants.LINE_BREAK));
            buildStatus.append("Por favor, regresse ao parque e estacione o veículo apropriadamente.");

        }
        return buildStatus;
    }
}
