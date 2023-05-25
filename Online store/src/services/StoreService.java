package services;

import models.Costumer;
import models.product.Product;
import repositories.IDatabaseRepository;

import java.util.*;

public class StoreService implements IStoreService{

    Set<Product> products = new HashSet<>();
    List<Costumer> costumers = new ArrayList<>();
    IDatabaseRepository databaseRepository;

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
    }

    public void listAllProducts() {
        for (Map.Entry<Integer, Product> entry : databaseRepository.getAllProducts().entrySet()) {
            int key = entry.getKey();
            System.out.println(key + ": " + entry.getValue().toString());
        }
    }

    public void addStock(String name, int stock) {
        for(Product p: products) {
            if(p.getName().equalsIgnoreCase(name)) {
                p.setStock(stock);
                break;
            }
        }
    }

    public void addReview(String name, String review) {
        for(Product p: products)
            if(p.getName().equalsIgnoreCase(name)) {
                p.addReview(review);
                break;
            }
    }

    public void addCostumer(Costumer c) {
        costumers.add(c);
    }

    public void listAllCostumers() {
        for(Costumer c: costumers){
            System.out.println(c.toString());
        }
    }

    public boolean verify(String name) {
        for(Costumer c: costumers)
            if(c.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }

    public boolean verifyProduct(String name) {
        for(Product p: products)
            if(p.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }

    public void modifyPrice(String name, int price) {
        for(Product p: products) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.setPrice(price);
                break;
            }
        }
    }

    public void addItemInCart(String name,String prodName) {
        for(Costumer c: costumers)
            if(c.getName().equalsIgnoreCase(name)) {
                c.addItemInCart(prodName);
                break;
            }
    }

    public void printCartInfo(String name) {
        for(Costumer c: costumers)
            if(c.getName().equalsIgnoreCase(name)) {
                System.out.println(c.getCartInfo());
                break;
            }
    }

    public void modifyAddress(String name, String address) {
        for(Costumer c: costumers)
            if(c.getName().equalsIgnoreCase(name)) {
                c.setAddress(address);
                break;
            }
    }

    public void modifyEmail(String name, String email) {
        for(Costumer c: costumers)
            if(c.getName().equalsIgnoreCase(name)) {
                c.setEmail(email);
                break;
            }
    }

    public void modifyPhoneNumber(String name, String phoneNumber) {
        for(Costumer c: costumers)
            if(c.getName().equalsIgnoreCase(name)) {
                c.setPhoneNumber(phoneNumber);
                break;
            }
    }
}
