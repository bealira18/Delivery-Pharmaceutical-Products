package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Pharmacy;
import oracle.jdbc.OracleTypes;

public class PharmacyDB extends DataHandler {

    public boolean addPharmacy(Pharmacy p, int limit) throws SQLException {

        openConnection();

        try {
            return addPharmacy(p.getName(), p.getAddress(), limit);

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addPharmacy(String name, String address, int limit) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addPharmacy(?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, address);
            callStmt.setInt(3, limit);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
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

            return new Pharmacy(pID, pName, pAddress);
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
}
