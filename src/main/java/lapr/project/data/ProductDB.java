package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

public class ProductDB extends DataHandler {

    public List<Product> getProducts() throws SQLException {

        List<Product> listProd = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getProducts() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {

                int prodId = rs.getInt(1);
                String prodName = rs.getString(2);
                double prodPrice = rs.getDouble(3);
                double prodWeight = rs.getDouble(4);
                int prodIdCategory = rs.getInt(5);

                listProd.add(new Product(prodId, prodName, prodPrice, prodWeight, prodIdCategory));
            }
            return listProd;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            if (callStmt != null) {
                callStmt.close();

                if (rs != null) {
                    rs.close();
                }
            }
            closeAll();
        }
    }
}
