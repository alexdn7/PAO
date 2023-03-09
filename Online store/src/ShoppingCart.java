import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
    private Costumer costumer;
    private Set<HashMap<Product, Integer>> products = new HashSet<HashMap<Product, Integer>>();

    public ShoppingCart(Costumer costumer) {
        this.costumer = costumer;
    }
}
