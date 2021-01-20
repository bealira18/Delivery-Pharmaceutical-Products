package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Date;
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

}
