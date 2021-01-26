package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Address;
import oracle.jdbc.OracleTypes;

public class AddressDB extends DataHandler {

    public boolean addAddress(Address a) {

        return addAddress(a.getDescription(), a.getLatitude(), a.getLongitude(), a.getAltitude());
    }

    public List<Address> getAddresses() {

        List<Address> la = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getAddresses() }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    while (rs.next()) {

                        String description = rs.getString(1);
                        double latitude1 = rs.getDouble(2);
                        double longitude1 = rs.getDouble(3);
                        double altitude1 = rs.getDouble(4);

                        la.add(new Address(description, latitude1, longitude1, altitude1));
                    }
                    return la;
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            closeAll();
        }
    }

    public Address getAddressByAd(String address) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getAddress(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setString(2, address);
                callStmt1.execute();

                try (ResultSet rs1 = (ResultSet) callStmt1.getObject(1)) {

                    if (rs1.next()) {
                        String ad = rs1.getString(1);
                        double latitude = rs1.getDouble(2);
                        double longitude = rs1.getDouble(3);
                        double altitude = rs1.getDouble(4);

                        return new Address(ad, latitude, longitude, altitude);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return null;
    }

    public boolean doesAddressExist(String addressName) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ ? = call doesAddressExist(?) }")) {

                callStmt2.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt2.setString(2, addressName);
                callStmt2.execute();

                return callStmt2.getInt(1) == 0;
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public List<Address> getPharmacyAddresses() {

        List<Address> la = new ArrayList<>();

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ ? = call getPharmacyAddresses() }")) {

                callStmt3.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt3.execute();

                try (ResultSet rs2 = (ResultSet) callStmt3.getObject(1)) {

                    while (rs2.next()) {

                        String description = rs2.getString(1);
                        double latitude2 = rs2.getDouble(2);
                        double longitude2 = rs2.getDouble(3);
                        double altitude2 = rs2.getDouble(4);

                        la.add(new Address(description, latitude2, longitude2, altitude2));
                    }
                    return la;
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            closeAll();
        }
    }

    public Address getAddressPharmacyById(int idPharmacy) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ ? = call getAddressPharmacyById(?) }")) {

                callStmt4.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt4.setInt(2, idPharmacy);
                callStmt4.execute();

                try (ResultSet rs3 = (ResultSet) callStmt4.getObject(1)) {

                    if (rs3.next()) {

                        String description = rs3.getString(1);
                        double latitude2 = rs3.getDouble(2);
                        double longitude2 = rs3.getDouble(3);
                        double altitude2 = rs3.getDouble(4);

                        return new Address(description, latitude2, longitude2, altitude2);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return null;
    }

    private boolean addAddress(String description, double latitude, double longitude, double altitude) {

        try (Connection con5 = getConnection()) {

            try (CallableStatement callStmt5 = con5.prepareCall("{ call addAddress(?,?,?,?) }")) {

                callStmt5.setString(1, description);
                callStmt5.setDouble(2, latitude);
                callStmt5.setDouble(3, longitude);
                callStmt5.setDouble(4, altitude);

                callStmt5.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
