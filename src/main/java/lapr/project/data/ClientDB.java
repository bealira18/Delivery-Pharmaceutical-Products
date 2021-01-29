package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDB extends DataHandler {

    /**
     * Calls the data base to add a client
     *
     * @param email email
     * @param password password
     * @param name name
     * @param nif nif
     * @param creditCard credit card
     * @param expirationDate expiration Date
     * @param ccv ccv
     * @param address address
     * @param latitude latitude
     * @param longitude longitude
     * @param altitude altitude
     * @return true if it adds or false if some exception appears
     */
    public boolean addClient(String email, String password, String name, int nif, long creditCard, LocalDate expirationDate,
            short ccv, String address, double latitude, double longitude, double altitude) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call addClient(?,?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt.setString(1, email);
                callStmt.setString(2, password);
                callStmt.setString(3, name);
                callStmt.setInt(4, nif);
                callStmt.setLong(5, creditCard);
                callStmt.setDate(6, Date.valueOf(expirationDate));
                callStmt.setShort(7, ccv);
                callStmt.setString(8, address);
                callStmt.setDouble(9, latitude);
                callStmt.setDouble(10, longitude);
                callStmt.setDouble(11, altitude);

                callStmt.execute();
                return true;
            }

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to get the client info with his email
     *
     * @param emailClient email client
     * @return the client info
     */
    public Client getClientByEmail(String emailClient) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getClientByEmail(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setString(2, emailClient);
                callStmt1.execute();

                try (ResultSet rs = (ResultSet) callStmt1.getObject(1)) {

                    if (rs.next()) {

                        String email = rs.getString(1);
                        String name = rs.getString(2);
                        int nif = rs.getInt(3);
                        long creditCard = rs.getLong(4);
                        String addressName = rs.getString(5);
                        int credits = rs.getInt(6);

                        AddressDB addressDB = new AddressDB();
                        Address address = addressDB.getAddressByAd(addressName);

                        return new Client(email, "qwerty", name, nif, new CreditCard(creditCard, LocalDate.now(), (short) 123), address, credits);

                    } else {
                        throw new IllegalArgumentException("No Client with email:" + emailClient);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No Client with email:" + emailClient);

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to update the lient credits
     *
     * @param email email
     * @param creditsEarned credits earned
     */
    public void updateCredits(String email, int creditsEarned) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ call addClientCredits(?,?) }")) {

                callStmt2.setString(1, email);
                callStmt2.setInt(2, creditsEarned);

                callStmt2.execute();
            }

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
    }

    /**
     * Uses the credits
     *
     * @param email email
     * @param idInvoice invoice id
     * @return true if it uses the credits, false if some exception appears
     */
    public boolean useCredits(String email, int idInvoice) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call useCredits(?,?) }")) {

                callStmt3.setString(1, email);
                callStmt3.setInt(2, idInvoice);

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to get some client credits
     *
     * @param email email
     * @return the number os credits
     */
    public int getCreditsByClientEmail(String email) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ ? = call getCreditsByClientEmail(?) }")) {

                callStmt4.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt4.setString(2, email);

                callStmt4.execute();
                return callStmt4.getInt(1);
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to update the client credits
     *
     * @param email email
     * @param newCreditsAmount new credits amount
     * @return true if it updates the client credites or false if some exeption appears
     */
    public boolean updateCreditsClient(String email, int newCreditsAmount) {

        try (Connection con5 = getConnection()) {

            try (CallableStatement callStmt5 = con5.prepareCall("{ call updateCreditsClient(?,?) }")) {

                callStmt5.setString(1, email);
                callStmt5.setInt(2, newCreditsAmount);

                callStmt5.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
