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

        return addPharmacy(a.getDescription(), a.getLatitude(), a.getLongitude(), a.getAltitude(),
                p.getName(), limitScooterPark, limitDronePark);
    }

    public Pharmacy getPhamacyByID(int pharmacyID) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getPharmacyById(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, pharmacyID);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        int pID = rs.getInt(1);
                        String pName = rs.getString(2);
                        String pAddress = rs.getString(3);

                        return new Pharmacy(pID, pName, new Address(pAddress, 0, 0, 0));
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Pharmacy with ID:" + pharmacyID);

        } finally {
            closeAll();
        }
        return null;
    }

    public List<Pharmacy> getAllPharmacies() {

        ArrayList<Pharmacy> pharmacies = new ArrayList<>();

        try (Connection con1 = getConnection()) {

            try (Statement stmt1 = con1.createStatement()) {

                try (ResultSet rs1 = stmt1.executeQuery("SELECT id_pharmacy FROM pharmacy")) {

                    while (rs1.next()) {
                        pharmacies.add(getPhamacyByID(rs1.getInt(1)));
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return pharmacies;
    }

    public boolean updatePharmacy(int id, String name) {

        Pharmacy a = getPhamacyByID(id);
        if (a == null) {
            return false;
        }
        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ call updatePharmacy(?,?) }")) {

                callStmt2.setInt(1, id);
                callStmt2.setString(2, name);

                callStmt2.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    private boolean addPharmacy(String address, double lat, double lon, double alt, String name,
            int limitScooterPark, int limitDronePark) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call addPharmacy(?,?,?,?,?,?,?) }")) {

                callStmt3.setString(1, address);
                callStmt3.setDouble(2, lat);
                callStmt3.setDouble(3, lon);
                callStmt3.setDouble(4, alt);
                callStmt3.setString(5, name);
                callStmt3.setInt(6, limitScooterPark);
                callStmt3.setInt(7, limitDronePark);

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public Pharmacy getPhamacyByAddress(Address pharmacyAddress) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ ? = call getPharmacyByAddress(?) }")) {

                callStmt4.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt4.setString(2, pharmacyAddress.getDescription());

                callStmt4.execute();

                try (ResultSet rs4 = (ResultSet) callStmt4.getObject(1)) {

                    if (rs4.next()) {
                        int pID = rs4.getInt(1);
                        String pName = rs4.getString(2);
                        String pAddress = rs4.getString(3);

                        return new Pharmacy(pID, pName, pharmacyAddress);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Pharmacy with Address:" + pharmacyAddress);

        } finally {
            closeAll();
        }
        return null;
    }
}
