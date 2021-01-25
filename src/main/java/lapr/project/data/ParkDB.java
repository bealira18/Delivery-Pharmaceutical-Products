package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Park;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkDB extends DataHandler {

    public boolean addPark(Park p) {

        return addPark(p.getPharmacyId(), p.getLimit(), p.getNumChargingStations(),
                p.getCategory(), p.getAddress().getDescription(), p.getMaxChargingPotency());
    }

    public int getLimitVehiclesPark(int idPharmacy, String vehicleType) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getLimitVehiclesPark(?,?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setInt(2, idPharmacy);
                callStmt.setString(3, vehicleType);

                callStmt.execute();

                return callStmt.getInt(1);
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;

        } finally {
            closeAll();
        }
    }

    public int getNumberOfScootersInPharmacy(int idPharmacy) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getNumberOfScootersInPharmacy(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt1.setInt(2, idPharmacy);
                callStmt1.execute();

                return callStmt1.getInt(1);
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;

        } finally {
            closeAll();
        }
    }

    public int getNumberOfDronesInPharmacy(int idPharmacy) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ ? = call getNumberOfDronesInPharmacy(?) }")) {

                callStmt2.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt2.setInt(2, idPharmacy);
                callStmt2.execute();

                return callStmt2.getInt(1);
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;

        } finally {
            closeAll();
        }
    }

    public Park getParkById(int id) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ ? = call getParkById(?) }")) {

                callStmt3.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt3.setInt(2, id);

                callStmt3.execute();

                try (ResultSet rs = (ResultSet) callStmt3.getObject(1)) {

                    if (rs.next()) {
                        int idP = rs.getInt(1);
                        int pharmacyId = rs.getInt(2);
                        int limit = rs.getInt(3);
                        int numChargingStations = rs.getInt(4);
                        String category = rs.getString(5);
                        String address = rs.getString(6);
                        double maxChargingPotency = rs.getDouble(7);

                        AddressDB adb = new AddressDB();
                        Address a = adb.getAddressByAd(address);

                        return new Park(idP, pharmacyId, limit, numChargingStations, category, a, maxChargingPotency);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Park with id:" + id);

        } finally {
            closeAll();
        }
        return null;
    }

    public boolean updateChargingStations(Park park) {

        Park p = getParkById(park.getScooterParkId());
        if (p == null) {
            return false;
        }

        try (Connection con4 = getConnection()) {

            openConnection();

            try (CallableStatement callStmt4 = con4.prepareCall("{ call updateNrChargingStations(?,?) }")) {

                callStmt4.setInt(1, park.getScooterParkId());
                callStmt4.setInt(2, park.getNumChargingStations());

                callStmt4.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    private boolean addPark(int pharmacyId, int limit, int numChargingStations, String category, String address,
            double maxChargingPotency) {

        try (Connection con5 = getConnection()) {

            try (CallableStatement callStmt5 = con5.prepareCall("{ call addPark(?,?,?,?,?,?) }")) {

                callStmt5.setInt(1, pharmacyId);
                callStmt5.setInt(2, limit);
                callStmt5.setInt(3, numChargingStations);
                callStmt5.setString(4, category);
                callStmt5.setString(5, address);
                callStmt5.setDouble(6, maxChargingPotency);

                callStmt5.execute();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public boolean updateParkChargingPotency(int idPark, double maxChargingPotency) {

        try (Connection con6 = getConnection()) {

            try (CallableStatement callStmt6 = con6.prepareCall("{ call updateParkChargingPotency(?,?) }")) {

                callStmt6.setInt(1, idPark);
                callStmt6.setDouble(2, maxChargingPotency);

                callStmt6.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
