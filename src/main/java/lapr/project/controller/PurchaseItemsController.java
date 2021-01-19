package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.data.ProductLineDB;
import lapr.project.data.PurchaseOrderDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.ProductCategory;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.ProductCategory;
import lapr.project.model.ProductLine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseItemsController {

    private final PharmacyDB pharmacyDB;
    private final ProductDB productDB;
    private HashMap<ProductCategory, List<Product>> mapProducts;
    private HashMap<Product, Integer> basket;
    private PurchaseOrderDB po;
    private ProductLineDB pl;
    private StockDB s;

    public PurchaseItemsController() {
        pharmacyDB = new PharmacyDB();
        productDB = new ProductDB();
        basket = new HashMap<>();
    }

    public PurchaseItemsController(PharmacyDB pharmacyDB, ProductDB productDB) {
        this.pharmacyDB = pharmacyDB;
        this.productDB = productDB;
        basket = new HashMap<>();
        po=new PurchaseOrderDB();
        pl=new ProductLineDB();
        s=new StockDB();
    }

    public PurchaseItemsController(PharmacyDB pharmacyDB, ProductDB productDB, PurchaseOrderDB po, ProductLineDB pl,StockDB s) {
        this.pharmacyDB = pharmacyDB;
        this.productDB = productDB;
        basket = new HashMap<>();
        this.po=po;
        this.pl=pl;
        this.s=s;
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
        if(basket.containsKey(p)) {
            quantity = basket.get(p);
            quantity += quantiyToAdd;
            basket.put(p, quantity);
        }
        else {
            basket.put(p, quantiyToAdd);
        }
        //se nao houver stock retornar false
        return true;
    }

    public boolean purchaseItems(int idOrder,int idPharmacy,String email) throws SQLException {

        if(basket.isEmpty())
            return false;

        if(!po.newOrder(idOrder,idPharmacy,email))
            return false;

        for(Map.Entry<Product,Integer> p : basket.entrySet()){
            if(!pl.newProductLine(idOrder,p.getKey().getId(),p.getValue(),p.getKey().getPrice()))
                return false;
        }
        s.updateProductStockAfterSale(idOrder);

        return true;
    }

}
