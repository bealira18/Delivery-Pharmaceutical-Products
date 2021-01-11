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
            closeAll();
            return false;
        }
    }

    private boolean addCourier(String email, String name, int nif, long socialSecurity, int pharmacyId, double weight) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?) }");

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

    public Courier getCourier(String email) throws SQLException{
        Courier c=null;
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getCourier(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(2, email);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                //Courier(String email, String password, String name, int nif, long socialSecurity, int pharmacyId, double weight)
                String em=rSet.getString(1);
                String password=rSet.getString(2);
                String name=rSet.getString(3);
                int nif=rSet.getInt(4);
                long socialSecurity=rSet.getLong(5);
                int pharmacyId=rSet.getInt(6);
                double weight=rSet.getDouble(7);

                c=new Courier(em,password,name,nif,socialSecurity,pharmacyId,weight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Courier with email:" + email);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return c;
    }

    public List<Courier> getAllAvailableCouriers(int orderId) {
        ArrayList<Courier> couriers = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rSet = null;
        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getAllAvailableCouriers(?) }");;

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
            closeAll();
        }
        return couriers;
    }

    public boolean updateCourier(String email,Courier c){
        Courier a;

        try{
            a=getCourier(email);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        try{
            a.setName(c.getName());
            a.setPharmacyId(c.getPharmacyId());
            a.setWeight(c.getWeight());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
