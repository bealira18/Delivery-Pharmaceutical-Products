package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClientDB extends DataHandler {
    
    public boolean addClient(String email, String password, String name, int nif, long creditCard, LocalDate expirationDate, short ccv, String address, double latitude, double longitude, double altitude) {

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
            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?,?,?,?,?) }");

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

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
