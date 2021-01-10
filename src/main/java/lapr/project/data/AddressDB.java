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

    public List<Address> getAddresses() throws SQLException {

        List<Address> la = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
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
