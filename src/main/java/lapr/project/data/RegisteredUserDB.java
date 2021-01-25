package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.RegisteredUser;
import oracle.jdbc.OracleTypes;

public class RegisteredUserDB extends DataHandler {

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
