package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Address;
import lapr.project.model.Path;
import oracle.jdbc.OracleTypes;

public class PathDB extends DataHandler {

    public List<Path> getPaths(List<Address> la) throws SQLException {

        List<Path> lp = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
            openConnection();

            callStmt.getConnection().prepareCall("{ ? = call getPaths() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {

                Address a1 = null;
                Address a2 = null;

                String a1text = rs.getString(1);
                String a2text = rs.getString(2);
                double kc = rs.getDouble(3);
                double wa = rs.getDouble(4);
                double ws = rs.getDouble(5);

                for (Address a : la) {

                    if (a.getDescription().equals(a1text)) {
                        a1 = a;
                    }
                    if (a.getDescription().equals(a2text)) {
                        a2 = a;
                    }
                }
                if (a1 != null && a2 != null) {
                    lp.add(new Path(a1, a2, kc, wa, ws));
                }
            }
            return lp;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PathDB.class.getName()).log(Level.SEVERE, null, ex);
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
