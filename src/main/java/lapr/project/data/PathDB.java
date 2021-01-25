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
import lapr.project.model.Path;
import oracle.jdbc.OracleTypes;

public class PathDB extends DataHandler {

    public boolean addPath(Path p) {

        return addPath(p.getAddress1().getDescription(), p.getAddress2().getDescription(),
                p.getKineticCoeficient(), p.getWindAngle(), p.getWindSpeed());
    }

    public boolean updatePath(Path p) {

        return updatePath(p.getAddress1().getDescription(), p.getAddress2().getDescription(),
                p.getKineticCoeficient(), p.getWindAngle(), p.getWindSpeed());
    }

    public boolean removePath(Address a1, Address a2) {

        return removePath(a1.getDescription(), a2.getDescription());
    }

    public List<Path> getPaths(List<Address> la) {

        List<Path> lp = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getPaths() }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

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
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PathDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            closeAll();
        }
    }

    private boolean addPath(String address1, String address2, double kineticCoefficient, double windAngle, double windSpeed) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ call addPath(?,?,?,?,?) }")) {

                callStmt1.setString(1, address1);
                callStmt1.setString(2, address2);
                callStmt1.setDouble(3, kineticCoefficient);
                callStmt1.setDouble(4, windAngle);
                callStmt1.setDouble(5, windSpeed);

                callStmt1.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PathDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    private boolean updatePath(String address1, String address2, double kineticCoefficient, double windAngle, double windSpeed) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ call updatePath(?,?,?,?,?) }")) {

                callStmt2.setString(1, address1);
                callStmt2.setString(2, address2);
                callStmt2.setDouble(3, kineticCoefficient);
                callStmt2.setDouble(4, windAngle);
                callStmt2.setDouble(5, windSpeed);

                callStmt2.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PathDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    private boolean removePath(String aDesc1, String aDesc2) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call removePath(?,?) }")) {

                callStmt3.setString(1, aDesc1);
                callStmt3.setString(2, aDesc2);

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PathDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
