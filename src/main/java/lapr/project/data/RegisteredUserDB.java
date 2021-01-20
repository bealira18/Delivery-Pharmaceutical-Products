package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.RegisteredUser;
import oracle.jdbc.OracleTypes;

public class RegisteredUserDB extends DataHandler {

    public RegisteredUser findUser(String email, String password) {

        /* Objeto "callStmt" para invocar a função "findUser" armazenada na BD.
         *
         * FUNCTION findUser(email_pr VARCHAR2, password_pr VARCHAR2) RETURN MATCHING_USER.ref_cursor
         */
        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call findUser(?, ?) }");
            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o primeiro parâmetro de entrada da função "findUser".
            callStmt.setString(2, email);
            // Especifica o segundo parâmetro de entrada da função "findUser".
            callStmt.setString(3, password);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String userEmail = rSet.getString(1);
                String userPassword = rSet.getString(2);
                String userRole = rSet.getString(3);

                return new RegisteredUser(userEmail, userPassword, userRole);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        finally{
            closeAll();
        }
        throw new IllegalArgumentException("Could not find a user matching this user and password");
    }
}
