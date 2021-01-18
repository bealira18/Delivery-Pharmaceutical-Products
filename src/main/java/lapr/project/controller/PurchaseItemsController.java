package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductCategoryDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchaseItemsController {

    private final PharmacyDB pharmacyDB;
    private final ProductDB productDB;
    private HashMap<ProductCategory, ArrayList<Product>> mapProducts;
    private HashMap<Product, Integer> basket;

    public PurchaseItemsController() {
        pharmacyDB = new PharmacyDB();
        productDB = new ProductDB();
        basket = new HashMap<>();
    }

    public PurchaseItemsController(PharmacyDB pharmacyDB, ProductDB productDB) {
        this.pharmacyDB = pharmacyDB;
        this.productDB = productDB;
        basket = new HashMap<>();
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacyDB.getAllPharmacies();
    }

    public void getProductsFromPharmacy(int idPharmacy) throws SQLException {
        mapProducts = productDB.getProductsFromPharmacy(idPharmacy);
    }

    public List<ProductCategory> getProductCategories() {
        List categories = new ArrayList();
        for(ProductCategory pc : mapProducts.keySet()) {
            categories.add(pc);
        }

        return categories;
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
}
