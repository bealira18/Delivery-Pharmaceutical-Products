/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Scanner;
import lapr.project.data.EmailService;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;


/**
 *
 * @author Ricardo
 */
public class VehicleParkingController {
    
    public final VehicleDB vDB;
    public final EmailService eS;

    public VehicleParkingController(VehicleDB vDB, EmailService eS) {
        this.vDB = vDB;
        this.eS = eS;
    }

    public VehicleParkingController() {
        this.vDB = new VehicleDB();
        this.eS = new EmailService();
    }

    public void interpretChargerInfo(String fileName) {
        
        try {
            File fileBuffer = new File(System.getProperty("charger.comm.dir") + fileName);
            Scanner sc = new Scanner(fileBuffer);
            String buffer = sc.nextLine();
            
            String[] arrBuffer = buffer.split(";");
            
            if (arrBuffer.length != 3)
            {
                throw new IllegalArgumentException("Unexpected number of arguments");
            }
            
            sc.close();
            
            //Check if the vehicle ID is real and what type it is
            String vehicleType = vDB.typeOfVehicleByID(Integer.parseInt(arrBuffer[1]));
            List<String> nameNemail = vDB.getEmailNameFromParkedVehicleResponsible(Integer.parseInt(arrBuffer[1]));
            LocalDateTime date;
            
            date = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(arrBuffer[0])), ZoneId.of("Z"));
            
            sendStatusEmail(nameNemail.get(1), nameNemail.get(0), date, Integer.parseInt(arrBuffer[1]), vehicleType, Float.parseFloat(arrBuffer[2]));
                        
        } catch (FileNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void writeChargerRequest(Vehicle vehicle, boolean isReal)
    {
        LocalDateTime date = LocalDateTime.now();
        long timestamp = date.toEpochSecond(ZoneOffset.UTC);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("'lock_'yyyy'_'MM'_'dd'_'HH'_'mm'_'ss'.data'");
        String fileName = System.getProperty("charger.comm.dir") + date.format(dtf);

        String buffer;
        
        buffer = String.format("%d;%d", timestamp, vehicle.getIdVehicle());
        
        if (isReal)
        {
            //Yes, I'm aware I'm writing longs to a system that expects ints: timestamp wont be a problem until 2038, the others will never crack the integer limit.
            buffer = String.format("%s;%d;%d", buffer, Math.round(vehicle.getMaxBattery()), Math.round(vehicle.getCurrentBattery()));
        }
        
        Utils.writeFile(buffer, fileName);
        Utils.writeFile(" ", fileName+".flag");
    }

    private void sendStatusEmail(String email, String name, LocalDateTime date, int vehicleID, String vehicleType, float timeToFull) {
        StringBuilder buildStatus = new StringBuilder("Caro Sr/a ");
        buildStatus.append(name).append(",");
        buildStatus.append(System.getProperty(Constants.LINE_BREAK));
        buildStatus.append(System.getProperty(Constants.LINE_BREAK));
        buildStatus.append("O veículo do tipo ").append(vehicleType).append(" de ID ").append(vehicleID).append(" estacionado às ");
        buildStatus.append(date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        
        if(timeToFull < 0.0)
        {
            buildStatus.append(" foi mal estacionado.").append(System.getProperty(Constants.LINE_BREAK));
            buildStatus.append("Por favor, regresse ao parque e estacione o veículo apropriadamente.");
        }
        else
        {
            buildStatus.append(" foi propriamente estacionado.").append(System.getProperty(Constants.LINE_BREAK));
            buildStatus.append(String.format("A bateria desta estará carregada em %.2f horas.", timeToFull));
        }
        
        eS.sendEmail(email, "Notificação de estacionamento", buildStatus.toString());
    }    
}
