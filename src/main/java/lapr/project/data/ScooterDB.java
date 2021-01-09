package lapr.project.data;

import lapr.project.model.Scooter;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterDB extends DataHandler {

    public boolean addScooter(Scooter scooter)throws SQLException {

        openConnection();

        try{
            return addScooter(scooter.getIdVehicle(), scooter.getIdPharmacy(), scooter.getWeight(), scooter.getAerodynamicCoeficient(),
                                scooter.getFrontalArea(), scooter.getMotor(), scooter.getCurrentBattery(), scooter.getMaxBattery(), scooter.getScooterStatusId());

        }catch (NullPointerException | SQLException ex){
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean addScooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
                              double motor, double currentBattery, double maxBattery, int scooterStatusId) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addScooter(?,?,?,?) }");

            callStmt.setInt(1, idScooter);
            callStmt.setInt(2, idPharmacy);
            callStmt.setDouble(3, weight);
            callStmt.setDouble(4, aerodynamicCoeficient);
            callStmt.setDouble(5, frontalArea);
            callStmt.setDouble(6, motor);
            callStmt.setDouble(7, currentBattery);
            callStmt.setDouble(8, maxBattery);
            callStmt.setInt(9, scooterStatusId);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

}
