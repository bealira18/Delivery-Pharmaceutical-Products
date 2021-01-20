package lapr.project.data;

import lapr.project.model.Stock;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockDB extends DataHandler {

    public boolean addProductToPharmacyCatalog(Stock stock)throws SQLException {

        openConnection();

        try{
            return addProductToPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());

        }catch (NullPointerException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean removeProductFromPharmacyCatalog(Stock stock)throws SQLException {

        openConnection();

        try{
            return removeProductFromPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());

        }catch (NullPointerException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addProductToPharmacyCatalog(int idPharmacy, int idProduct)throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addProductToPharmacyCatalog(?,?) }");

            callStmt.setInt(1, idPharmacy);
            callStmt.setInt(2, idProduct);

            callStmt.execute();
            return true;

        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            closeAll();
        }
        return false;
    }

    private boolean removeProductFromPharmacyCatalog(int idPharmacy, int idProduct)throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call removeProductFromCatalog(?,?) }");

            callStmt.setInt(1, idPharmacy);
            callStmt.setInt(2, idProduct);

            callStmt.execute();
            return true;

        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            closeAll();
        }
        return false;
    }

    public boolean checkIfProductExistsInCatalog(int idPharmacy, int idProduct) throws SQLException {
        CallableStatement callStmt = null;

        try{
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call checkIfProductExistsInCatalog(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
            callStmt.setInt(3, idProduct);
            callStmt.execute();

            return callStmt.getInt(1) > 0;
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
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
            return true;

        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            closeAll();
        }
        return false;
    }

    public boolean checkIfIsEnoughStock(int idOrder) throws SQLException {
        CallableStatement callStmt = null;

        try{
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call checkIfIsEnoughStock(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idOrder);
            callStmt.execute();

            return callStmt.getInt(1) > 0;
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean checkIfIsEnoughStockInOtherPharmacy(int idOrder) throws SQLException {
        CallableStatement callStmt = null;

        try{
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call checkIfIsEnoughStockInOtherPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idOrder);
            callStmt.execute();

            return callStmt.getInt(1) > 0;
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }
}
