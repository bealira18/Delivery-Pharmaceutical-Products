package lapr.project.data;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lapr.project.model.Drone;
import oracle.jdbc.OracleTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DroneDB extends DataHandler {

    public boolean addDrone(Drone drone, int width, int height) {

        String path="./qrDrone"+drone.getIdVehicle()+".png";

        try{
            generateQRCodeImage(drone.toString(),width,height,path);
        } catch (WriterException | IOException ex){
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return addDrone(drone.getIdVehicle(), drone.getIdPharmacy(), drone.getWeight(), drone.getAerodynamicCoeficient(),
                drone.getFrontalArea(), drone.getMotor(), drone.getCurrentBattery(), drone.getMaxBattery(), drone.getAverageSpeed(),
                drone.getWidth(), drone.getAverageVerticalSpeed(), drone.getDroneStatusId(), path);
    }

    /**
     * Calls the data base to get the drone info related to a drone id
     *
     * @param idDrone drone id
     * @return returns the drone with the requested id
     */
    public Drone getIdDrone(int idDrone) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getDroneById(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, idDrone);
                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        int id = rs.getInt(1);
                        double width = rs.getDouble(2);
                        double averageVerticalSpeed = rs.getDouble(3);
                        int droneStatus = rs.getInt(4);

                        return new Drone(id, width, averageVerticalSpeed, droneStatus);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Drone with id:" + idDrone);

        } finally {
            closeAll();
        }
        return null;
    }

    /**
     * Calls the data base to update a drone
     *
     * @param idd drone id
     * @param d drone
     * @return true if it updates or false otherwise
     */
    public boolean updateDrone(int idd, Drone d) {

        Drone drone = getIdDrone(idd);
        if (drone == null) {
            return false;
        }

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ call updateDrone(?,?,?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt1.setInt(1, d.getIdVehicle());
                callStmt1.setDouble(2, d.getWidth());
                callStmt1.setDouble(3, d.getAverageVerticalSpeed());
                callStmt1.setInt(4, d.getDroneStatusId());
                callStmt1.setInt(5, d.getIdPharmacy());
                callStmt1.setDouble(6, d.getWeight());
                callStmt1.setDouble(7, d.getAerodynamicCoeficient());
                callStmt1.setDouble(8, d.getFrontalArea());
                callStmt1.setDouble(9, d.getMotor());
                callStmt1.setDouble(10, d.getCurrentBattery());
                callStmt1.setDouble(11, d.getMaxBattery());
                callStmt1.setDouble(12, d.getAverageSpeed());

                callStmt1.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Get all the drones that are available
     *
     * @param orderId order id
     * @return the drones available
     */
    public List<Drone> getAllAvailableDrones(int orderId) {

        ArrayList<Drone> drones = new ArrayList<>();

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ ? = call getAllAvailableDrones(?) }")) {

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2, orderId);

                callStmt2.execute();

                try (ResultSet rs1 = (ResultSet) callStmt2.getObject(1)) {

                    while (rs1.next()) {
                        drones.add(getIdDrone(rs1.getInt(1)));
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return drones;
    }

    /**
     * Calls the data base to add a Drone
     *
     * @param idDrone drone id
     * @param idPharmacy pharmacy id
     * @param weight weight
     * @param aerodynamicCoeficient aerodynamic coeficient
     * @param frontalArea frontal area
     * @param motor motor
     * @param currentBattery current battery
     * @param maxBattery max battery
     * @param averageSpeed avarage speed
     * @param width width
     * @param averageVerticalSpeed average vertical speed
     * @param droneStatusId drone status id
     * @param path path
     * @return true if it adds or false if any exeption appears
     */
    private boolean addDrone(int idDrone, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
            double motor, double currentBattery, double maxBattery, double averageSpeed, double width, double averageVerticalSpeed,
            int droneStatusId, String path) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call addDrone(?,?,?,?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt3.setInt(1, idDrone);
                callStmt3.setInt(2, idPharmacy);
                callStmt3.setDouble(3, weight);
                callStmt3.setDouble(4, aerodynamicCoeficient);
                callStmt3.setDouble(5, frontalArea);
                callStmt3.setDouble(6, motor);
                callStmt3.setDouble(7, currentBattery);
                callStmt3.setDouble(8, maxBattery);
                callStmt3.setDouble(9, averageSpeed);
                callStmt3.setDouble(10, width);
                callStmt3.setDouble(11, averageVerticalSpeed);
                callStmt3.setInt(12, droneStatusId);

                File imgFile=new File(path);
                FileInputStream fin=new FileInputStream(imgFile);
                callStmt3.setBinaryStream(13,fin,imgFile.length());

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException | FileNotFoundException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public Drone getHighestBatteryDrone(int pharmacyId) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ ? = call getHighestBatteryDrone(?) }")) {

                callStmt4.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt4.setInt(2, pharmacyId);

                callStmt4.execute();

                try (ResultSet rs4 = (ResultSet) callStmt4.getObject(1)) {

                    if (rs4.next()) {
                        int idDrone = rs4.getInt(1);
                        int idPharmacy = rs4.getInt(2);
                        double weight = rs4.getDouble(3);
                        double aerodynamicCoefficient = rs4.getDouble(4);
                        double frontalArea = rs4.getDouble(5);
                        double motor = rs4.getDouble(6);
                        double currentBattery = rs4.getDouble(7);
                        double maxBattery = rs4.getDouble(8);
                        double averageSpeed = rs4.getDouble(9);
                        double width = rs4.getDouble(12);
                        double averageVerticalSpeed = rs4.getDouble(13);

                        return new Drone(idDrone, idPharmacy, weight, aerodynamicCoefficient, frontalArea, motor, currentBattery,
                                maxBattery, averageSpeed, width, averageVerticalSpeed, 1);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return null;
    }

    public void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

}
