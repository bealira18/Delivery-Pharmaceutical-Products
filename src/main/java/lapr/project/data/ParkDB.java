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

    public boolean addPark(Park p) throws SQLException {

        openConnection();

        try {
            return addPark(p.getPharmacyId(), p.getLimit(), p.getNumChargingStations(), p.getCategory(), p.getAddress().getDescription());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addPark(int pharmacyId, int limit, int numChargingStations, String category, String address) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addPark(?,?,?,?,?) }");

            callStmt.setInt(1, pharmacyId);
            callStmt.setInt(2, limit);
            callStmt.setInt(3, numChargingStations);
            callStmt.setString(4, category);
            callStmt.setString(5, address);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
        return false;
    }

    public int getLimitVehiclesPark(int idPharmacy, String vehicleType) throws SQLException {
        CallableStatement callStt = null;

        try {
            openConnection();

            callStt = getConnection().prepareCall("{ ? = call getLimitVehiclesPark(?,?) }");

            callStt.registerOutParameter(1, OracleTypes.INTEGER);
            callStt.setInt(2, idPharmacy);
            callStt.setString(3, vehicleType);
            callStt.execute();

            return callStt.getInt(1);
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (callStt != null) {
                callStt.close();
            }
            closeAll();
        }
    }

    public int getNumberOfScootersInPharmacy(int idPharmacy) throws SQLException {
        CallableStatement callStmt1 = null;

        try {
            openConnection();

            callStmt1 = getConnection().prepareCall("{ ? = call getNumberOfScootersInPharmacy(?) }");

            callStmt1.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt1.setInt(2, idPharmacy);
            callStmt1.execute();

            return callStmt1.getInt(1);
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (callStmt1 != null) {
                callStmt1.close();
            }
            closeAll();
        }
    }

    public int getNumberOfDronesInPharmacy(int idPharmacy) throws SQLException {
        CallableStatement callStmt2 = null;

        try {
            openConnection();

            callStmt2 = getConnection().prepareCall("{ ? = call getNumberOfDronesInPharmacy(?) }");

            callStmt2.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt2.setInt(2, idPharmacy);
            callStmt2.execute();

            return callStmt2.getInt(1);
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (callStmt2 != null) {
                callStmt2.close();
            }
            closeAll();
        }
    }

    public Park getParkById(int id) throws SQLException {

        Park p = null;

        CallableStatement callStmt3 = null;
        ResultSet rSet = null;

        try {
            openConnection();

            callStmt3 = getConnection().prepareCall("{ ? = call getParkById(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt3.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt3.setInt(2, id);
            // Executa a invocação da função "getSailor".
            callStmt3.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            rSet = (ResultSet) callStmt3.getObject(1);

            if (rSet.next()) {
                int idP = rSet.getInt(1);
                int pharmacyId = rSet.getInt(2);
                int limit = rSet.getInt(3);
                int numChargingStations = rSet.getInt(4);
                String category = rSet.getString(5);
                String address = rSet.getString(6);

                AddressDB adb = new AddressDB();
                Address a = adb.getAddressByAd(address);

                p = new Park(idP, pharmacyId, limit, numChargingStations, category, a);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Park with id:" + id);
        }

        return p;
    }

    public boolean updateChargingStations(Park park) throws SQLException {

        Park p;

        try {
            p = getParkById(park.getScooterParkId());
            if (p == null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ call updateNrChargingStations(?,?) }");

            callStmt.setInt(1, park.getScooterParkId());
            callStmt.setInt(2, park.getNumChargingStations());

            callStmt.execute();
            return true;
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
        return false;
    }
}
