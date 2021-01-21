package lapr.project.data;

import java.sql.CallableStatement;
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

        try {
            openConnection();
            return addAddress(a.getDescription(), a.getLatitude(), a.getLongitude(), a.getAltitude());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addAddress(String description, double latitude, double longitude, double altitude) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addAddress(?,?,?,?) }");

            callStmt.setString(1, description);
            callStmt.setDouble(2, latitude);
            callStmt.setDouble(3, longitude);
            callStmt.setDouble(4, altitude);

            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

    public List<Address> getAddresses() throws SQLException {

        List<Address> la = new ArrayList<>();
        CallableStatement callStmt1 = null;
        ResultSet rs1 = null;

        try {
            openConnection();

            callStmt1 = getConnection().prepareCall("{ ? = call getAddresses() }");
            callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt1.execute();

            rs1 = (ResultSet) callStmt1.getObject(1);

            while (rs1.next()) {

                String description = rs1.getString(1);
                double latitude1 = rs1.getDouble(2);
                double longitude1 = rs1.getDouble(3);
                double altitude1 = rs1.getDouble(4);

                la.add(new Address(description, latitude1, longitude1, altitude1));
            }
            return la;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            if (callStmt1 != null) {
                callStmt1.close();

                if (rs1 != null) {
                    rs1.close();
                }
            }
            closeAll();
        }
    }

    public Address getAddressByAd(String address) throws SQLException {
        Address a = null;

        CallableStatement callStmt = null;

        try {
            openConnection();

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

                a = new Address(ad, latitude, longitude, altitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("This address doesn't exist:" + address);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }

        return a;
    }

    public boolean doesAddressExist(String addressName) throws SQLException {
        CallableStatement callStmt = null;
        int aux;

        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call doesAddressExist(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, addressName);
            callStmt.execute();

            aux = callStmt.getInt(1);
            return aux == 0;
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
    }

    public List<Address> getPharmacyAddresses() throws SQLException {

        List<Address> la = new ArrayList<>();
        CallableStatement callStmt2 = null;
        ResultSet rs2 = null;

        try {
            openConnection();

            callStmt2 = getConnection().prepareCall("{ ? = call getPharmacyAddresses() }");
            callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt2.execute();

            rs2 = (ResultSet) callStmt2.getObject(1);

            while (rs2.next()) {

                String description = rs2.getString(1);
                double latitude2 = rs2.getDouble(2);
                double longitude2 = rs2.getDouble(3);
                double altitude2 = rs2.getDouble(4);

                la.add(new Address(description, latitude2, longitude2, altitude2));
            }
            return la;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            if (callStmt2 != null) {
                callStmt2.close();

                if (rs2 != null) {
                    rs2.close();
                }
            }
            closeAll();
        }
    }
}
