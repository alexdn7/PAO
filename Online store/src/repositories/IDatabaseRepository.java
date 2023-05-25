package repositories;

import models.product.Product;
import java.util.Map;

public interface IDatabaseRepository {

    Map<Integer, Product> getAllProducts();
    void addProduct(Product product);
}
