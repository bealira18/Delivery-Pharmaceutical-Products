package lapr.project.data;

import lapr.project.model.Stock;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockDB extends DataHandler {

    public boolean addProductToPharmacyCatalog(Stock stock) throws SQLException {

        openConnection();

        try {
            return addProductToPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean removeProductFromPharmacyCatalog(Stock stock) throws SQLException {

        openConnection();

        try {
            return removeProductFromPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addProductToPharmacyCatalog(int idPharmacy, int idProduct) throws SQLException {

        CallableStatement callStmt1 = null;

        try {
            callStmt1 = getConnection().prepareCall("{ call addProductToPharmacyCatalog(?,?) }");

            callStmt1.setInt(1, idPharmacy);
            callStmt1.setInt(2, idProduct);

            callStmt1.execute();
            return true;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt1 != null) {
                callStmt1.close();
            }
            closeAll();
        }
        return false;
    }

    private boolean removeProductFromPharmacyCatalog(int idPharmacy, int idProduct) throws SQLException {

        CallableStatement callStmt2 = null;

        try {
            callStmt2 = getConnection().prepareCall("{ call removeProductFromCatalog(?,?) }");

            callStmt2.setInt(1, idPharmacy);
            callStmt2.setInt(2, idProduct);

            callStmt2.execute();
            return true;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt2 != null) {
                callStmt2.close();
            }
            closeAll();
        }
        return false;
    }

    public boolean checkIfProductExistsInCatalog(int idPharmacy, int idProduct) throws SQLException {
        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call checkIfProductExistsInCatalog(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
            callStmt.setInt(3, idProduct);
            callStmt.execute();

            return callStmt.getInt(1) > 0;
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
    }

    public boolean updateProductStockAfterSale(int idOrder) throws SQLException {
        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ call updateProductStockAfterSale(?) }");

            callStmt.setInt(1, idOrder);

            callStmt.execute();
            closeAll();
            return true;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
        }
        return false;
    }

    public boolean checkIfIsEnoughStock(int idPharmacy, int idProduct, int productQuantity) throws SQLException {
        CallableStatement callStm = null;

        try {
            openConnection();

            callStm = getConnection().prepareCall("{ ? = call checkIfIsEnoughStock(?,?,?) }");

            callStm.registerOutParameter(1, OracleTypes.INTEGER);
            callStm.setInt(2, idPharmacy);
            callStm.setInt(3, idProduct);
            callStm.setInt(4, productQuantity);
            callStm.execute();

            return callStm.getInt(1) == 1;
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (callStm != null) {
                callStm.close();
            }
            closeAll();
        }
    }








    /*public boolean checkIfIsEnoughStockInOtherPharmacy(int idOrder) throws SQLException {
        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call checkIfIsEnoughStockInOtherPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idOrder);
            callStmt.execute();

            return callStmt.getInt(1) > 0;
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            closeAll();
        }
    }
     */
}
