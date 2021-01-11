package lapr.project.data;

import lapr.project.model.Courier;
import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            callStmt.getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?) }");

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

    public Scooter getIdScooter(int idScooter) throws SQLException{
        Scooter s = null;
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getScooterById(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setInt(2, idScooter);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                int scooterStatus = rSet.getInt(2);

                s = new Scooter(id, scooterStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Scooter with id:" + idScooter);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return s;
    }

    public List<Scooter> getAllAvailableScooters(int orderId) {
        ArrayList<Scooter> scooters = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rSet = null;
        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getAllAvailableScooters(?) }");;

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, orderId);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                scooters.add(getIdScooter(rSet.getInt(1)));
            }
        } catch (SQLException e) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        return scooters;
    }

    public boolean updateScooter(int ids,int idp,Scooter s){
        Scooter a;

        try{
            a=getIdScooter(ids);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        try{
            a.setWeight(s.getWeight());
            a.setFrontalArea(s.getFrontalArea());
            a.setMotor(s.getMotor());
            a.setCurrentBattery(s.getCurrentBattery());
            a.setMaxBattery(s.getMaxBattery());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
