package lapr.project.data;

import lapr.project.model.Drone;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DroneDB extends DataHandler {

    public boolean addDrone(Drone drone) throws SQLException {

        openConnection();

        try {
            return addDrone(drone.getIdVehicle(), drone.getIdPharmacy(), drone.getWeight(), drone.getAerodynamicCoeficient(),
                    drone.getFrontalArea(), drone.getMotor(), drone.getCurrentBattery(), drone.getMaxBattery(), drone.getAverageSpeed(),
                    drone.getWidth(), drone.getAverageVerticalSpeed(), drone.getDroneStatusId());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean addDrone(int idDrone, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
            double motor, double currentBattery, double maxBattery,  double averageSpeed, double width, double averageVerticalSpeed,
                            int droneStatusId) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addDrone(?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, idDrone);
            callStmt.setInt(2, idPharmacy);
            callStmt.setDouble(3, weight);
            callStmt.setDouble(4, aerodynamicCoeficient);
            callStmt.setDouble(5, frontalArea);
            callStmt.setDouble(6, motor);
            callStmt.setDouble(7, currentBattery);
            callStmt.setDouble(8, maxBattery);
            callStmt.setDouble(9, averageSpeed);
            callStmt.setDouble(10, width);
            callStmt.setDouble(11, averageVerticalSpeed);
            callStmt.setInt(12, droneStatusId);

            callStmt.execute();
            return true;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
        return false;
    }

    public Drone getIdDrone(int idDrone) throws SQLException {
        Drone d = null;
        CallableStatement callStmt = null;
        ResultSet rSet = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getDroneById(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idDrone);
            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                double width = rSet.getDouble(2);
                double averageVerticalSpeed = rSet.getDouble(3);
                int droneStatus = rSet.getInt(4);

                d = new Drone(id, width, averageVerticalSpeed, droneStatus);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Drone with id:" + idDrone);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            if (rSet != null) {
                rSet.close();
            }
            closeAll();
        }
        return d;
    }

    public boolean updateDrone(int idd, Drone d) throws SQLException {
        Drone drone;

        try {
            drone = getIdDrone(idd);
            if (drone == null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ call updateDrone(?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, d.getIdVehicle());
            callStmt.setDouble(2, d.getWidth());
            callStmt.setDouble(3, d.getAverageVerticalSpeed());
            callStmt.setInt(4, d.getDroneStatusId());
            callStmt.setInt(5, d.getIdPharmacy());
            callStmt.setDouble(6, d.getWeight());
            callStmt.setDouble(7, d.getAerodynamicCoeficient());
            callStmt.setDouble(8, d.getFrontalArea());
            callStmt.setDouble(9, d.getMotor());
            callStmt.setDouble(10, d.getCurrentBattery());
            callStmt.setDouble(11, d.getMaxBattery());
            callStmt.setDouble(12, d.getAverageSpeed());


            callStmt.execute();
            closeAll();
            return true;
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        }
        return false;
    }

    public List<Drone> getAllAvailableDrones(int orderId) throws SQLException {
        ArrayList<Drone> drones = new ArrayList<>();
        CallableStatement callStt = null;
        ResultSet rSet = null;
        try {
            openConnection();

            callStt = getConnection().prepareCall("{ ? = call getAllAvailableDrones(?) }");

            callStt.registerOutParameter(1, OracleTypes.CURSOR);

            callStt.setInt(2, orderId);

            callStt.execute();

            rSet = (ResultSet) callStt.getObject(1);

            while (rSet.next()) {
                drones.add(getIdDrone(rSet.getInt(1)));
            }
        } catch (SQLException e) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (callStt != null) {
                callStt.close();
            }
            if (rSet != null) {
                rSet.close();
            }
            closeAll();
        }
        return drones;
    }

}
