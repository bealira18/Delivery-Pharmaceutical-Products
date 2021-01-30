package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.RegisteredUser;
import oracle.jdbc.OracleTypes;

/**
 * Handles database interactions related to RegisteredUser.
 * 
 * @author lapr3-2020-g052
 */
public class RegisteredUserDB extends DataHandler {

    /**
     * Finds a user that matches a given email and password.
     * @param email the users email.
     * @param password the users password.
     * @return RegisteredUser with the user matching those values or throws
     * IllegalArgumentException if none is found.
     */
    public RegisteredUser findUser(String email, String password) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call findUser(?, ?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, email);
                callStmt.setString(3, password);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        String userEmail = rs.getString(1);
                        String userPassword = rs.getString(2);
                        String userRole = rs.getString(3);

                        return new RegisteredUser(userEmail, userPassword, userRole);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(RegisteredUserDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Could not find a user matching this user and password");
    }
}
