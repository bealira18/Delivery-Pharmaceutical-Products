package lapr.project.data;

import lapr.project.model.ScooterPark;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterParkDB extends DataHandler {

    public void updateNrChargingStations(ScooterPark sp) throws SQLException {

        updateNrChargingStations(sp.getNumChargingStations(),sp.getId());
    }

    public void updateNrChargingStations(int nrCS,int id) throws SQLException {

        CallableStatement callStmt = null;

        try{
            openConnection();
            callStmt = getConnection().prepareCall("{ call nrChargingStations(?,?) }");

            callStmt.setInt(1,nrCS);
            //callStmt.setInt(2,id);

            callStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ScooterParkDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
    }

}
