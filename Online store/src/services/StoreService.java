package services;

import models.product.Product;
import repositories.IDatabaseRepository;

import java.util.*;

public class StoreService implements IStoreService{

    IDatabaseRepository databaseRepository;
    private final AuditService auditService = new AuditService();

    private StoreService(IDatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    private static class SingletonHolder {
        public static StoreService createInstance(IDatabaseRepository databaseRepository) {
            return new StoreService(databaseRepository);
        }
    }

    public static StoreService getInstance(IDatabaseRepository databaseRepository) {
        return SingletonHolder.createInstance(databaseRepository);
    }

    public void addProduct(Product product) {
        databaseRepository.addProduct(product);
        auditService.logAction("addProduct");
    }

    public void listAllProducts() {
        for (Map.Entry<Integer, Product> entry : databaseRepository.getAllProducts().entrySet()) {
            int key = entry.getKey();
            System.out.println(key + ": " + entry.getValue().toString());
        }
        auditService.logAction("listAllProducts");
    }

    public void listAllProductsSortedByName() {
        List<Product> productList = new ArrayList<>(databaseRepository.getAllProducts().values());
        productList.sort(Comparator.comparing(Product::getName));

        for (Product product : productList) {
            System.out.println(product.toString());
        }
        auditService.logAction("listAllProductsSortedByName");
    }

    public void listAllProductsSortedByPrice() {
        List<Product> productList = new ArrayList<>(databaseRepository.getAllProducts().values());
        productList.sort(Comparator.comparing(Product::getPrice));

        for (Product product : productList) {
            System.out.println(product.toString());
        }
        auditService.logAction("listAllProductsSortedByPrice");
    }

    public void addStock(int productId, int stock) {
        databaseRepository.addStock(productId, stock);
        auditService.logAction("addStock");
    }

    public void addReview(int productId, String review) {
        databaseRepository.addReview(productId, review);
        auditService.logAction("addReview");
    }

    public boolean verifyProduct(int productId) {
        return databaseRepository.verifyProduct(productId);
    }

    public void modifyPrice(int productId, double price) {
        databaseRepository.modifyPrice(productId, price);
        auditService.logAction("modifyPrice");
    }

    public void deleteProductById(int id) {
        databaseRepository.deleteProduct(id);
        auditService.logAction("deleteProductById");
    }

}
