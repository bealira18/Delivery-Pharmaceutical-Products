package lapr.project.data;

import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DroneDB extends DataHandler {

    public boolean addDrone(Drone drone)throws SQLException {

        openConnection();

        try{
            return addDrone(drone.getIdVehicle(), drone.getIdPharmacy(), drone.getWeight(), drone.getAerodynamicCoeficient(),
                    drone.getFrontalArea(), drone.getMotor(), drone.getCurrentBattery(), drone.getMaxBattery(), drone.getDroneStatusId());

        }catch (NullPointerException | SQLException ex){
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean addDrone(int idDrone, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
                              double motor, double currentBattery, double maxBattery, int droneStatusId) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, idDrone);
            callStmt.setInt(2, idPharmacy);
            callStmt.setDouble(3, weight);
            callStmt.setDouble(4, aerodynamicCoeficient);
            callStmt.setDouble(5, frontalArea);
            callStmt.setDouble(6, motor);
            callStmt.setDouble(7, currentBattery);
            callStmt.setDouble(8, maxBattery);
            callStmt.setInt(9, droneStatusId);

            callStmt.execute();
            return true;

        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(DroneDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

    public Drone getIdDrone(int idDrone) throws SQLException {
        Drone d = null;
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getDroneById(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setInt(2, idDrone);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                int droneStatus = rSet.getInt(2);

                d = new Drone(id, droneStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Drone with id:" + idDrone);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return d;
    }

    public boolean updateDrone(int idd, Drone d) throws SQLException {
        Drone a;

        try{
            a=getIdDrone(idd);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        CallableStatement callStmt = null;

        try{
            callStmt.getConnection().prepareCall("{ call updateDrone(?,?,?,?,?,?,?) }");

            callStmt.setInt(1,d.getIdVehicle());
            callStmt.setInt(2,d.getIdPharmacy());
            callStmt.setDouble(3,d.getWeight());
            callStmt.setDouble(4,d.getFrontalArea());
            callStmt.setDouble(5,d.getMotor());
            callStmt.setDouble(6,d.getCurrentBattery());
            callStmt.setDouble(7,d.getMaxBattery());
            return true;
        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }


}
