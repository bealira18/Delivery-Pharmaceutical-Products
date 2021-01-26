package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Graph;

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
    private final UpdateScooterController updateScooterController;
    private final NotifyClientController notifyClientController;
    private Map<ProductCategory, List<Product>> mapProducts;
    private final Map<Product, Integer> basket;

    public PurchaseItemsController() {

        pharmacyDB = new PharmacyDB();
        productDB = new ProductDB();
        basket = new HashMap<>();
        po = new PurchaseOrderDB();
        pl = new ProductLineDB();
        s = new StockDB();
        notifyClientController = new NotifyClientController();
        updateScooterController = new UpdateScooterController();
    }

    public PurchaseItemsController(PharmacyDB pharmacyDB, ProductDB productDB, PurchaseOrderDB po,
            ProductLineDB pl, StockDB s, NotifyClientController notifyClientController, UpdateScooterController usc) {

        this.pharmacyDB = pharmacyDB;
        this.productDB = productDB;
        basket = new HashMap<>();
        this.po = po;
        this.pl = pl;
        this.s = s;
        this.notifyClientController = notifyClientController;
        this.updateScooterController = usc;
    }

    public List<Pharmacy> getPharmacies() {

        return pharmacyDB.getAllPharmacies();
    }

    public void getProductsFromPharmacy(int idPharmacy) {

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
        return true;
    }

    //the graph received as parameter must be graphScooterEnergy
    public PurchaseOrder purchaseItems(int idOrder, int idPharmacy, String email, Graph<Address, Path> graph) {
        Pharmacy pharmacy = pharmacyDB.getPhamacyByID(idPharmacy);

        if (basket.isEmpty()) {
            return null;
        }
        if (!checkOrderWeight()) {
            return null;
        }
        if (!checkForStock(pharmacy, graph)) {
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

        return new PurchaseOrder(idOrder, idPharmacy, email, LocalDate.now());
    }

    public boolean checkForStock(Pharmacy pharmacy, Graph<Address, Path> graph) {

        for (Map.Entry<Product, Integer> p : basket.entrySet()) {
            if (!notifyClientController.checkForStock(pharmacy, p.getKey(), p.getValue(), graph)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkOrderWeight() {
        double totalWeight = 0;

        for (Map.Entry<Product, Integer> p : basket.entrySet()) {
            totalWeight = totalWeight + (p.getKey().getWeight() * p.getValue());
        }
        return totalWeight <= updateScooterController.getScooterMaxPayload();
    }
}
