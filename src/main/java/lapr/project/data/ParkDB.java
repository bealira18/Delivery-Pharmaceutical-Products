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

    //Para duplicação receber o call por string e juntar as duas funções em uma
    public int getLimitScooterPark(int idPharmacy) throws SQLException {
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getLimitScooterPark(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
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

    public int getNumberOfScootersInPharmacy(int idPharmacy) throws SQLException{
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getNumberOfScootersInPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
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

    public int getLimitDronePark(int idPharmacy) throws SQLException {
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getLimitDronePark(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
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

    public int getNumberOfDronesInPharmacy(int idPharmacy) throws SQLException{
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getNumberOfDronesInPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
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
