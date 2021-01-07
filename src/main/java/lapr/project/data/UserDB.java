/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Ricardo
 */
public class UserDB extends DataHandler {
    
    public User findUser(String email, String password) {

        /* Objeto "callStmt" para invocar a função "findUser" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors; //TODO STUFF
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call findUser(?, ?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o primeiro parâmetro de entrada da função "findUser".
            callStmt.setString(2, email);
            // Especifica o segundo parâmetro de entrada da função "findUser".
            callStmt.setString(2, password);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String userEmail = rSet.getString(1);
                String userPassword = rSet.getString(2);
                String userRole = rSet.getString(3);

                return new User(userEmail, userPassword, userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Could not find a user matching this user and password");
    }
        
    public boolean addUser(String email, String password, String role) {

        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addUser" armazenado
             *  na BD.
             *
             *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER) //TODO: REWRITE
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call addUser(?,?,?,?) }");

            callStmt.setString(1, email);
            callStmt.setString(2, password);
            callStmt.setString(3, role);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
