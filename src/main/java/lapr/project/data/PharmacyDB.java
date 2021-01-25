package lapr.project.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import oracle.jdbc.OracleTypes;

public class PharmacyDB extends DataHandler {

    public boolean addPharmacy(Address a, Pharmacy p, int limitScooterPark, int limitDronePark) {
        return addPharmacy(a.getDescription(), a.getLatitude(), a.getLongitude(), a.getAltitude(), p.getName(), limitScooterPark, limitDronePark);
    }

    public Pharmacy getPhamacyByID(int pharmacyID) {

        Pharmacy p;

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getPharmacyById(?) }")) {

                p = callGetPharmacy(pharmacyID, callStmt);

            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Pharmacy with ID:" + pharmacyID);
        } finally {
            closeAll();
        }
        return p;
    }

    public List<Pharmacy> getAllPharmacies() {
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (Statement stmt = con.createStatement()) {

                try (ResultSet rs = stmt.executeQuery("SELECT id_pharmacy FROM pharmacy")) {

                    while (rs.next()) {
                        pharmacies.add(getPhamacyByID(rs.getInt(1)));
                    }

                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        return pharmacies;
    }

    public boolean updatePharmacy(int id, String name) {
        Pharmacy a;

        try {
            a = getPhamacyByID(id);
            if (a == null) return false;
        } catch (NullPointerException e) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call updatePharmacy(?,?) }")) {

                callStmt.setInt(1, id);
                callStmt.setString(2, name);

                callStmt.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return false;
    }

    private boolean addPharmacy(String address, double lat, double lon, double alt, String name, int limitScooterPark, int limitDronePark) {

        try (Connection con = getConnection()) {
            try (CallableStatement callStmt = con.prepareCall("{ call addPharmacy(?,?,?,?,?,?,?) }")) {

                callStmt.setString(1, address);
                callStmt.setDouble(2, lat);
                callStmt.setDouble(3, lon);
                callStmt.setDouble(4, alt);
                callStmt.setString(5, name);
                callStmt.setInt(6, limitScooterPark);
                callStmt.setInt(7, limitDronePark);

                callStmt.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    private static Pharmacy callGetPharmacy(int identifier, CallableStatement callStmt) throws SQLException {
        callStmt.registerOutParameter(1, OracleTypes.CURSOR); //standard para o output

        callStmt.setInt(2, identifier);

        callStmt.execute();

        ResultSet rSet = (ResultSet) callStmt.getObject(1);

        return elaboratePharmacy(rSet);
    }

    private static Pharmacy elaboratePharmacy(ResultSet rSet) throws SQLException {
        if (rSet.next()) {
            int pID = rSet.getInt(1);
            String pName = rSet.getString(2);
            String pAddress = rSet.getString(3);

            return new Pharmacy(pID, pName, new Address(pAddress, 0, 0, 0));
        }
        return null;
    }

}
