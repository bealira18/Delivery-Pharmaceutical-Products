package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import oracle.jdbc.OracleTypes;

public class PharmacyDB extends DataHandler {

    public boolean addPharmacy(Address a, Pharmacy p, int limitScooterPark, int limitDronePark) throws SQLException {

        openConnection();

        try {
            return addPharmacy(a.getDescription(), a.getLatitude(), a.getLongitude(), a.getAltitude(), p.getName(), limitScooterPark, limitDronePark);

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addPharmacy(String address, double lat, double lon, double alt, String name, int limitScooterPark, int limitDronePark) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?,?,?) }");

            callStmt.setString(1, address);
            callStmt.setDouble(2, lat);
            callStmt.setDouble(3, lon);
            callStmt.setDouble(4, alt);
            callStmt.setString(5, name);
            callStmt.setInt(6, limitScooterPark);
            callStmt.setInt(7, limitDronePark);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
    }

    private static Pharmacy callGetPharmacy(String identifier, CallableStatement callStmt) throws SQLException {
        callStmt.registerOutParameter(1, OracleTypes.CURSOR); //standard para o output

        callStmt.setString(2, identifier);

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

    public Pharmacy getPhamacyByID(String pharmacyID) throws SQLException {

        CallableStatement callStmt = null;
        Pharmacy p = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyById(?) }");
            p = callGetPharmacy(pharmacyID, callStmt);
        } catch (SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Pharmacy with ID:" + pharmacyID);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return p;
    }

    public List<Pharmacy> getAllPharmacies() {
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            openConnection();

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery("SELECT id_pharmacy FROM pharmacy");

            while (rs.next()) {
                pharmacies.add(getPhamacyByID(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            closeAll();
        }
        return pharmacies;
    }

    public boolean updatePharmacy(int id,String name) throws SQLException {
        Pharmacy a;

        String i=Integer.toString(id);

        try{
            a=getPhamacyByID(i);
            if (a == null) return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ call updatePharmacy(?,?) }");

            callStmt.setInt(1,id);
            callStmt.setString(2,name);

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
}
