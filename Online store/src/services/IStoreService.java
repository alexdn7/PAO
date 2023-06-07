package services;

import models.product.Product;

public interface IStoreService {

    void addProduct(Product product);

    void listAllProducts();

    void listAllProductsSortedByName();

    void listAllProductsSortedByPrice();

    void addStock(int productId, int stock);

    void addReview(int productId, String review);

    void deleteProductById(int id);

    boolean verifyProduct(int productId);

    void modifyPrice(int productId, double price);
}
