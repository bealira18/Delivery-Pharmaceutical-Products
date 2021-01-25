package lapr.project.data;

import lapr.project.model.Drone;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DroneDB extends DataHandler {

    public boolean addDrone(Drone drone) {

        return addDrone(drone.getIdVehicle(), drone.getIdPharmacy(), drone.getWeight(), drone.getAerodynamicCoeficient(),
                drone.getFrontalArea(), drone.getMotor(), drone.getCurrentBattery(), drone.getMaxBattery(), drone.getAverageSpeed(),
                drone.getWidth(), drone.getAverageVerticalSpeed(), drone.getDroneStatusId());
    }

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

    private boolean addDrone(int idDrone, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
            double motor, double currentBattery, double maxBattery, double averageSpeed, double width, double averageVerticalSpeed,
            int droneStatusId) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call addDrone(?,?,?,?,?,?,?,?,?,?,?,?) }")) {

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

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
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
                        double width = rs4.getDouble(10);
                        double averageVerticalSpeed = rs4.getDouble(11);

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
}
