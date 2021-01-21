package lapr.project.data;

import lapr.project.model.Courier;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourierDB extends DataHandler {

    public boolean addCourier(Courier c) throws SQLException {

        openConnection();

        try {
            return addCourier(c.getEmail(), c.getName(), c.getNif(), c.getSocialSecurity(), c.getPharmacyId(), c.getWeight());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    private boolean addCourier(String email, String name, int nif, long socialSecurity, int pharmacyId, double weight) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?) }");

            callStmt.setInt(1, pharmacyId);
            callStmt.setString(2, email);
            callStmt.setString(3, name);
            callStmt.setInt(4, nif);
            callStmt.setLong(5, socialSecurity);
            callStmt.setDouble(6, weight);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

    public Courier getCourier(String email) throws SQLException {
        Courier c;
        CallableStatement callStmt = null;
        ResultSet rSet = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourier(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(2, email);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                //Courier(String email, String password, String name, int nif, long socialSecurity, int pharmacyId, double weight)
                String em = rSet.getString(1);
                //String password=rSet.getString(2);
                String name = rSet.getString(2);
                int nif = rSet.getInt(3);
                long socialSecurity = rSet.getLong(4);
                int pharmacyId = rSet.getInt(5);
                double weight = rSet.getDouble(6);

                c = new Courier(em, "qwerty", name, nif, socialSecurity, pharmacyId, weight);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Courier with email:" + email);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            if (rSet != null) {
                rSet.close();
            }
        }
        return c;
    }

    public List<Courier> getAllAvailableCouriers(int orderId) throws SQLException {
        ArrayList<Courier> couriers = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rSet = null;
        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getAllAvailableCouriers(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, orderId);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                couriers.add(getCourier(rSet.getString(1)));
            }
        } catch (SQLException e) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            if (rSet != null) {
                rSet.close();
            }
            closeAll();
        }
        return couriers;
    }

    public boolean updateCourier(String email, Courier c) throws SQLException {
        Courier a;
        CallableStatement callStmt = null;

        try {
            openConnection();

            a = getCourier(email);
            if (a == null) {
                return false;
            }
            callStmt = getConnection().prepareCall("{ call updateCourier(?,?,?,?) }");

            callStmt.setString(1, c.getEmail());
            callStmt.setInt(2, c.getPharmacyId());
            callStmt.setString(3, c.getName());
            callStmt.setDouble(4, c.getWeight());

            callStmt.execute();

            return true;
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
        return false;
    }
}
