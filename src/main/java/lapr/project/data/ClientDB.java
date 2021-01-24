package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDB extends DataHandler {

    public boolean addClient(String email, String password, String name, int nif, long creditCard, LocalDate expirationDate,
                             short ccv, String address, double latitude, double longitude, double altitude) throws SQLException {

        CallableStatement callStmt = null;

        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addClient" armazenado
             *  na BD.
             *
             *  PROCEDURE addClient(email_pr VARCHAR2,
             *  password_pr VARCHAR2,
             *  name_pr VARCHAR2,
             *  nif_pr NUMBER,
             *  credit_card_pr NUMBER,
             *  validity_date_pr DATE,
             *  ccv_pr SMALLINT,
             *  adr_pr VARCHAR2,
             *  latitude_pr NUMERIC,
             *  longitude_pr NUMERIC,
             *  altitude_pr NUMERIC)
             */
            callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?,?,?,?,?) }");

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

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
        return true;
    }

    public Client getClientByEmail(String emailClient) throws SQLException {
        Client c;
        CallableStatement callStmt = null;
        ResultSet rSet = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getClientByEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, emailClient);
            callStmt.execute();
            rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String email = rSet.getString(1);
                String name = rSet.getString(2);
                int nif = rSet.getInt(3);
                long creditCard = rSet.getLong(4);
                String addressName = rSet.getString(5);
                int credits = rSet.getInt(6);

                AddressDB addressDB = new AddressDB();

                Address address = addressDB.getAddressByAd(addressName);

                c=new Client(email,"qwerty", name, nif, new CreditCard(creditCard, LocalDate.now(), (short) 123), address, credits);
            }
            else{
                throw new IllegalArgumentException("No Client with email:" + emailClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Client with email:" + emailClient);
        } finally {
            if(callStmt!=null)
                callStmt.close();
            if(rSet!=null)
                rSet.close();
        }
        return c;
    }

    public void updateCredits(String email, int creditsEarned) throws SQLException {

        CallableStatement callStmt = null;

        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addClientCredits" armazenado
             *  na BD.
             *
             *  CREATE OR REPLACE PROCEDURE addClientCredits(email_pr VARCHAR2, credits_pr INT)
             */
            callStmt = getConnection().prepareCall("{ call addClientCredits(?,?) }");

            callStmt.setString(1, email);
            callStmt.setInt(2, creditsEarned);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
    }

    public boolean useCredits(String email, int idInvoice) throws SQLException {

        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ call useCredits(?,?) }");

            callStmt.setString(1, email);
            callStmt.setInt(2, idInvoice);
            callStmt.execute();
            return true;
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
        return false;
    }

    public int getCreditsByClientEmail(String email) throws SQLException {

        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getCreditsByClientEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, email);

            callStmt.execute();
            return callStmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
    }

    public boolean updateCreditsClient(String email, int newCreditsAmount) throws SQLException {

        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ call updateCreditsClient(?,?) }");

            callStmt.setString(1, email);
            callStmt.setInt(2, newCreditsAmount);
            callStmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
    }

}
