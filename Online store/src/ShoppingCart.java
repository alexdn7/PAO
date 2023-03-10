import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<String, Integer> products = new HashMap<>();
    public ShoppingCart() {}

    public void addInCart(String name) {
        if(products.get(name) != null) {
            var value = products.get(name) + 1;
            products.put(name, value);
        }
        else
            products.put(name, 1);
    }

    public Map<String, Integer> getProducts() {
        return products;
    }
}
