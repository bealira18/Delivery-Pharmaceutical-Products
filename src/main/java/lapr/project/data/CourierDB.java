package lapr.project.data;

import lapr.project.model.Courier;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourierDB extends DataHandler {

    /**
     * Calls the data base to add a courier
     *
     * @param c courier
     * @return true if it adds the courier or false otherwise
     */
    public boolean addCourier(Courier c) {

        return addCourier(c.getEmail(), c.getName(), c.getNif(), c.getSocialSecurity(), c.getPharmacyId(), c.getWeight());
    }

    /**
     * Calls the data base to get a courier
     *
     * @param email email
     * @return a courier
     */
    public Courier getCourier(String email) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getCourier(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, email);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        String em = rs.getString(1);
                        String name = rs.getString(2);
                        int nif = rs.getInt(3);
                        long socialSecurity = rs.getLong(4);
                        int pharmacyId = rs.getInt(5);
                        double weight = rs.getDouble(6);

                        return new Courier(em, "qwerty", name, nif, socialSecurity, pharmacyId, weight);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Courier with email:" + email);

        } finally {
            closeAll();
        }
        return null;
    }

    /**
     * Calls the data base to get all the available couriers
     *
     * @param orderId order id
     * @return a list of couriers
     */
    public List<Courier> getAllAvailableCouriers(int orderId) {

        ArrayList<Courier> couriers = new ArrayList<>();

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getAllAvailableCouriers(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setInt(2, orderId);

                callStmt1.execute();

                try (ResultSet rs1 = (ResultSet) callStmt1.getObject(1)) {

                    while (rs1.next()) {
                        couriers.add(getCourier(rs1.getString(1)));
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return couriers;
    }

    /**
     * Calls the data base to update the courier
     *
     * @param email email
     * @param c courier
     * @return true if it updates the courier or false if some exception appears
     */
    public boolean updateCourier(String email, Courier c) {

        try (Connection con2 = getConnection()) {

            Courier a = getCourier(email);
            if (a == null) {
                return false;
            }
            try (CallableStatement callStmt2 = con2.prepareCall("{ call updateCourier(?,?,?,?) }")) {

                callStmt2.setString(1, c.getEmail());
                callStmt2.setInt(2, c.getPharmacyId());
                callStmt2.setString(3, c.getName());
                callStmt2.setDouble(4, c.getWeight());

                callStmt2.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to add a Courier
     *
     * @param email email
     * @param name name
     * @param nif nif
     * @param socialSecurity social security
     * @param pharmacyId pharmacy id
     * @param weight weight
     * @return true if it adds or false if some exception appears
     */
    private boolean addCourier(String email, String name, int nif, long socialSecurity, int pharmacyId, double weight) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call addCourier(?,?,?,?,?,?) }")) {

                callStmt3.setInt(1, pharmacyId);
                callStmt3.setString(2, email);
                callStmt3.setString(3, name);
                callStmt3.setInt(4, nif);
                callStmt3.setLong(5, socialSecurity);
                callStmt3.setDouble(6, weight);

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
