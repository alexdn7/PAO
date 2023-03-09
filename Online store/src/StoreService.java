import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StoreService {

    List<Product> products = new ArrayList<>();
    List<Costumer> costumers = new ArrayList<>();

    private StoreService() {}

    private static class SingletonHolder {
        public static final StoreService INSTANCE = new StoreService();
    }

    public static StoreService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void listAllProducts() {
        for(Product p: products){
            System.out.println(p.getStock());
        }

    }

    public void addStock(String name, int stock) {
        for(Product p: products) {
            if(p.getName().equals(name))
                p.setStock(stock);
        }
    }

    public void addReview(String name, String review) {
        for(Product p: products)
            if(p.getName().equals(name))
                p.addReview(review);
    }

    public void addCostumer(Costumer c) {
        costumers.add(c);
    }

    public void listAllCostumers() {
        for(Costumer c: costumers){
            c.printCostumerInfo();
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
}
