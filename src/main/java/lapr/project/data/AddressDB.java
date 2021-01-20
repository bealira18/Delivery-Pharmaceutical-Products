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

    public boolean addAddress(Address a) throws SQLException {

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

        }
        return false;
    }

    public List<Address> getAddresses() throws SQLException {

        List<Address> la = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
            openConnection();

            callStmt.getConnection().prepareCall("{ ? = call getAddresses() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {

                String description = rs.getString(1);
                double latitude = rs.getDouble(2);
                double longitude = rs.getDouble(3);
                double altitude = rs.getDouble(4);

                la.add(new Address(description, latitude, longitude, altitude));
            }
            return la;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
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
            closeAll();
        }
    }

    public List<Address> getPharmacyAddresses() throws SQLException {

        List<Address> la = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
            openConnection();

            callStmt.getConnection().prepareCall("{ ? = call getPharmacyAddresses() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {

                String description = rs.getString(1);
                double latitude = rs.getDouble(2);
                double longitude = rs.getDouble(3);
                double altitude = rs.getDouble(4);

                la.add(new Address(description, latitude, longitude, altitude));
            }
            return la;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddressDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            if (callStmt != null) {
                callStmt.close();

                if (rs != null) {
                    rs.close();
                }
            }
            closeAll();
        }
    }
}
