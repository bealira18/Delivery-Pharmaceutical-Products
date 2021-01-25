package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Product;
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

    public boolean addProductToPharmacyCatalog(Stock stock) {
        return addProductToPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());
    }

    public boolean removeProductFromPharmacyCatalog(Stock stock) {
        return removeProductFromPharmacyCatalog(stock.getPharmacyId(), stock.getProductId());
    }

    public boolean checkIfProductExistsInCatalog(int idPharmacy, int idProduct) {

        try (Connection con = getConnection()){

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

    public boolean updateProductStockAfterSale(int idOrder) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call updateProductStockAfterSale(?) }")) {

                callStmt.setInt(1, idOrder);

                callStmt.execute();
                closeAll();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    public int checkIfIsEnoughStock(int idPharmacy, int idProduct, int productQuantity) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStm = con.prepareCall("{ ? = call checkIfIsEnoughStock(?,?,?) }")) {

                callStm.registerOutParameter(1, OracleTypes.INTEGER);
                callStm.setInt(2, idPharmacy);
                callStm.setInt(3, idProduct);
                callStm.setInt(4, productQuantity);
                callStm.execute();

                return callStm.getInt(1);
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            closeAll();
        }
    }

    public List<Address> getOthersPharmacyAddressWithProductStock(int idPharmacy, int idProduct, int productQuantity) {

        List<Address> listAddress = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt1 = con.prepareCall("{ ? = call getOthersPharmacyAddressWithProductStock(?,?,?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt1.setInt(2, idPharmacy);
                callStmt1.setInt(3, idProduct);
                callStmt1.setInt(4, productQuantity);
                callStmt1.execute();

                try (ResultSet rs1 = (ResultSet) callStmt1.getObject(1)) {

                    while (rs1.next()) {

                        String address = rs1.getString(1);
                        double longitude = rs1.getDouble(2);
                        double latitude = rs1.getDouble(3);
                        double altitude = rs1.getDouble(4);

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

        try (Connection con = getConnection()) {
            try (CallableStatement callStmt = con.prepareCall("{ call backOrder(?,?,?,?) }")) {

                callStmt.setInt(1, idPharmacy1);
                callStmt.setInt(2, idPharmacy2);
                callStmt.setInt(3, idProduct);
                callStmt.setInt(4, productQuantity);

                callStmt.execute();
                return true;
            }
        }catch (NullPointerException | SQLException ex) {
            Logger.getLogger(StockDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            closeAll();
        }
    }

    private boolean addProductToPharmacyCatalog(int idPharmacy, int idProduct) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt1 = con.prepareCall("{ call addProductToPharmacyCatalog(?,?) }")) {

                callStmt1.setInt(1, idPharmacy);
                callStmt1.setInt(2, idProduct);

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

    private boolean removeProductFromPharmacyCatalog(int idPharmacy, int idProduct) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt2 = con.prepareCall("{ call removeProductFromCatalog(?,?) }")) {

                callStmt2.setInt(1, idPharmacy);
                callStmt2.setInt(2, idProduct);

                callStmt2.execute();
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
