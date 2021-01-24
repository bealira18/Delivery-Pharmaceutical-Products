package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.ProductCategory;
import lapr.project.model.PurchaseOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseItemsController {

    private final PharmacyDB pharmacyDB;
    private final ProductDB productDB;
    private final PurchaseOrderDB po;
    private final ProductLineDB pl;
    private final StockDB s;
    private Map<ProductCategory, List<Product>> mapProducts;
    private final Map<Product, Integer> basket;

    public PurchaseItemsController() {
        pharmacyDB = new PharmacyDB();
        productDB = new ProductDB();
        basket = new HashMap<>();
        po = new PurchaseOrderDB();
        pl = new ProductLineDB();
        s = new StockDB();
    }

    public PurchaseItemsController(PharmacyDB pharmacyDB, ProductDB productDB, PurchaseOrderDB po, ProductLineDB pl, StockDB s) {
        this.pharmacyDB = pharmacyDB;
        this.productDB = productDB;
        basket = new HashMap<>();
        this.po = po;
        this.pl = pl;
        this.s = s;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacyDB.getAllPharmacies();
    }

    public void getProductsFromPharmacy(int idPharmacy) throws SQLException {
        mapProducts = productDB.getProductsFromPharmacy(idPharmacy);
    }

    public List<ProductCategory> getProductCategories() {

        return new ArrayList<>(mapProducts.keySet());
    }

    public List<Product> getProductsFromCategory(ProductCategory pc) {
        return mapProducts.get(pc);
    }

    public boolean addToBasket(Product p, int quantiyToAdd) {
        int quantity;
        if (basket.containsKey(p)) {
            quantity = basket.get(p);
            quantity += quantiyToAdd;
            basket.put(p, quantity);
        } else {
            basket.put(p, quantiyToAdd);
        }
        //se nao houver stock retornar false
        return true;
    }

    public PurchaseOrder purchaseItems(int idOrder, int idPharmacy, String email) throws SQLException {

        if (basket.isEmpty()) {
            return null;
        }

        if (!po.newOrder(idOrder, idPharmacy, email)) {
            return null;
        }

        for (Map.Entry<Product, Integer> p : basket.entrySet()) {
            if (!pl.newProductLine(idOrder, p.getKey().getId(), p.getValue(), p.getKey().getPrice() * p.getValue())) {
                return null;
            }
        }
        s.updateProductStockAfterSale(idOrder);
        PurchaseOrder purchaseOrder = new PurchaseOrder(idOrder, idPharmacy, email, LocalDate.now());
        return purchaseOrder;
    }
}
