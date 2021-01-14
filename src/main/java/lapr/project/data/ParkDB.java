package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Park;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkDB extends DataHandler {

    public int getLimitVehiclesPark(int idPharmacy, String vehicleType) throws SQLException {
        CallableStatement callStt = null;

        try{
            callStt = getConnection().prepareCall("{ ? = call getLimitVehiclesPark(?,?) }");

            callStt.registerOutParameter(1, OracleTypes.INTEGER);
            callStt.setInt(2, idPharmacy);
            callStt.setString(3, vehicleType);
            callStt.execute();

            return callStt.getInt(1);
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally {
            if(callStt != null){
                callStt.close();
            }
        }
    }

    public int getNumberOfVehiclesInPharmacy(int idPharmacy, String vehicleType) throws SQLException{
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getNumberOfVehiclesInPharmacy(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
            callStmt.setString(3, vehicleType);
            callStmt.execute();

            return callStmt.getInt(1);
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally {
            if(callStmt != null){
                callStmt.close();
            }
        }
    }

    public Address getAddressByAd(String address) throws SQLException{
        Address a=null;

        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getAddress(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(2, address);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String ad = rSet.getString(1);
                double latitude = rSet.getDouble(2);
                double longitude = rSet.getDouble(3);
                double altitude = rSet.getDouble(4);

                a = new Address(ad, latitude,longitude,altitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("This address doesn't exist:" + address);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }

        return a;
    }

    public Park getParkById (int id) throws SQLException {

        Park p = null;

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkById(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setInt(2, id);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int idP = rSet.getInt(1);
                int pharmacyId = rSet.getInt(2);
                int limit = rSet.getInt(3);
                int numChargingStations = rSet.getInt(4);
                String category = rSet.getString(5);
                String address = rSet.getString(6);

                Address a = getAddressByAd(address);

                p = new Park(idP, pharmacyId, limit, numChargingStations, category, a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Park with id:" + id);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }

            return p;
        }
    }

    public boolean updateCS(Park park) throws SQLException {

        Park p;

        try{
            p=getParkById(park.getScooterParkId());
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        CallableStatement callStmt = null;

        try{
            callStmt.getConnection().prepareCall("{ call updateNrChargingStations(?,?) }");

            callStmt.setInt(1,p.getScooterParkId());
            callStmt.setInt(2,p.getNumChargingStations());
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
