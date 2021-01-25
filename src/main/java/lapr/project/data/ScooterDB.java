package lapr.project.data;

import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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

public class ScooterDB extends DataHandler {

    public boolean addScooter(Scooter scooter) {

        return addScooter(scooter.getIdVehicle(), scooter.getIdPharmacy(), scooter.getWeight(), scooter.getAerodynamicCoeficient(),
                scooter.getFrontalArea(), scooter.getMotor(), scooter.getCurrentBattery(), scooter.getMaxBattery(),
                scooter.getAverageSpeed(), scooter.getScooterStatusId());
    }

    public Scooter getIdScooter(int idScooter) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getScooterById(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, idScooter);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        int id = rs.getInt(1);
                        int scooterStatus = rs.getInt(2);

                        return new Scooter(id, scooterStatus);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Scooter with id:" + idScooter);

        } finally {
            closeAll();
        }
        return null;
    }

    public List<Scooter> getAllAvailableScooters(int orderId) {

        ArrayList<Scooter> scooters = new ArrayList<>();

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getAllAvailableScooters(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setInt(2, orderId);

                callStmt1.execute();

                try (ResultSet rs1 = (ResultSet) callStmt1.getObject(1)) {

                    while (rs1.next()) {
                        scooters.add(getIdScooter(rs1.getInt(1)));
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return scooters;
    }

    public boolean updateScooter(int ids, Scooter s) {

        Scooter a = getIdScooter(ids);
        if (a == null) {
            return false;
        }

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ call updateScooter(?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt2.setInt(1, s.getIdVehicle());
                callStmt2.setInt(2, s.getScooterStatusId());
                callStmt2.setInt(3, s.getIdPharmacy());
                callStmt2.setDouble(4, s.getWeight());
                callStmt2.setDouble(5, s.getAerodynamicCoeficient());
                callStmt2.setDouble(6, s.getFrontalArea());
                callStmt2.setDouble(7, s.getMotor());
                callStmt2.setDouble(8, s.getCurrentBattery());
                callStmt2.setDouble(9, s.getMaxBattery());
                callStmt2.setDouble(10, s.getAverageSpeed());

                callStmt2.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    private boolean addScooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
                               double motor, double currentBattery, double maxBattery, double averageSpeed, int scooterStatusId) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt3.setInt(1, idScooter);
                callStmt3.setInt(2, idPharmacy);
                callStmt3.setDouble(3, weight);
                callStmt3.setDouble(4, aerodynamicCoeficient);
                callStmt3.setDouble(5, frontalArea);
                callStmt3.setDouble(6, motor);
                callStmt3.setDouble(7, currentBattery);
                callStmt3.setDouble(8, maxBattery);
                callStmt3.setDouble(9, averageSpeed);
                callStmt3.setInt(10, scooterStatusId);

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public Scooter getHighestBatteryScooter(int pharmacyId) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt = con4.prepareCall("{ ? = call getHighestBatteryScooter(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, pharmacyId);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        int idScooter = rs.getInt(1);
                        int idPharmacy = rs.getInt(2);
                        double weight = rs.getDouble(3);
                        double aerodynamicCoefficient = rs.getDouble(4);
                        double frontalArea = rs.getDouble(5);
                        double motor = rs.getDouble(6);
                        double currentBattery = rs.getDouble(7);
                        double maxBattery = rs.getDouble(8);
                        double averageSpeed = rs.getDouble(9);

                        return new Scooter(idScooter, idPharmacy, weight, aerodynamicCoefficient, frontalArea, motor, currentBattery,
                                maxBattery, averageSpeed, 1);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
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
