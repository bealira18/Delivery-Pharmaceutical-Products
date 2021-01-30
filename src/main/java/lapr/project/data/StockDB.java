package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Stock;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockDB extends DataHandler {

    /**
     * Calls the data base to add a product in the pharmacy catalog
     *
     * @param stock stock
     * @return true if it adds or false otherwise
     */
    public boolean addProductToPharmacyCatalog(Stock stock) {

        return addProductToPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());
    }

    /**
     * Calls the data base to remove a product from the pharmacy catalog
     *
     * @param stock stock
     * @return true if it removes or false otherwise
     */
    public boolean removeProductFromPharmacyCatalog(Stock stock) {

        return removeProductFromPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());
    }

    /**
     * Calls the data base to check if a product exists in the catalog
     *
     * @param idPharmacy pharmacy id
     * @param idProduct product id
     * @return true if it exists or false otherwise
     */
    public boolean checkIfProductExistsInCatalog(int idPharmacy, int idProduct) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call checkIfProductExistsInCatalog(?,?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setInt(2, idPharmacy);
                callStmt.setInt(3, idProduct);
                callStmt.execute();

                return callStmt.getInt(1) > 0;
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to update product stock after sale
     *
     * @param idOrder order id
     * @return true if it removes or false otherwise
     */
    public boolean updateProductStockAfterSale(int idOrder) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ call updateProductStockAfterSale(?) }")) {

                callStmt1.setInt(1, idOrder);

                callStmt1.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to check if is enough stock
     *
     * @param idPharmacy pharmacy id
     * @param idProduct product id
     * @param productQuantity product quantity
     * @return number of products in that stock or -1 if doesn't have stock
     */
    public int checkIfIsEnoughStock(int idPharmacy, int idProduct, int productQuantity) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ ? = call checkIfIsEnoughStock(?,?,?) }")) {

                callStmt2.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt2.setInt(2, idPharmacy);
                callStmt2.setInt(3, idProduct);
                callStmt2.setInt(4, productQuantity);
                callStmt2.execute();

                return callStmt2.getInt(1);
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to get others pharmacy address with product stock
     *
     * @param idPharmacy pharmacy id
     * @param idProduct product id
     * @param productQuantity product quantity
     * @return the list of addresses of pharmacies that have enough stock
     */
    public List<Address> getOthersPharmacyAddressWithProductStock(int idPharmacy, int idProduct, int productQuantity) {

        List<Address> listAddress = new ArrayList<>();

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ ? = call getOthersPharmacyAddressWithProductStock(?,?,?) }")) {

                callStmt3.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt3.setInt(2, idPharmacy);
                callStmt3.setInt(3, idProduct);
                callStmt3.setInt(4, productQuantity);
                callStmt3.execute();

                try (ResultSet rs = (ResultSet) callStmt3.getObject(1)) {

                    while (rs.next()) {

                        String address = rs.getString(1);
                        double longitude = rs.getDouble(2);
                        double latitude = rs.getDouble(3);
                        double altitude = rs.getDouble(4);

                        listAddress.add(new Address(address, longitude, latitude, altitude));
                    }
                    return listAddress;
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return listAddress;

        } finally {
            closeAll();
        }
    }

    public boolean backOrder(int idPharmacy1, int idPharmacy2, int idProduct, int productQuantity) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ call performBackOrder(?,?,?,?) }")) {

                callStmt4.setInt(1, idPharmacy1);
                callStmt4.setInt(2, idPharmacy2);
                callStmt4.setInt(3, idProduct);
                callStmt4.setInt(4, productQuantity);

                callStmt4.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to add a product in the pharmacy catalog
     *
     * @param idPharmacy pharmacy id
     * @param idProduct product id
     * @return true if it adds or false otherwise
     */
    private boolean addProductToPharmacyCatalog(int idPharmacy, int idProduct) {

        try (Connection con5 = getConnection()) {

            try (CallableStatement callStmt5 = con5.prepareCall("{ call addProductToPharmacyCatalog(?,?) }")) {

                callStmt5.setInt(1, idPharmacy);
                callStmt5.setInt(2, idProduct);

                callStmt5.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Calls the data base to remove a product in the pharmacy catalog
     *
     * @param idPharmacy pharmacy id
     * @param idProduct product id
     * @return true if it removes or false otherwise
     */
    private boolean removeProductFromPharmacyCatalog(int idPharmacy, int idProduct) {

        try (Connection con6 = getConnection()) {

            try (CallableStatement callStmt6 = con6.prepareCall("{ call removeProductFromCatalog(?,?) }")) {

                callStmt6.setInt(1, idPharmacy);
                callStmt6.setInt(2, idProduct);

                callStmt6.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
