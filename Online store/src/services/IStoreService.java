package services;

import models.product.Product;

public interface IStoreService {

    void addProduct(Product product);

    void listAllProducts();

    void addStock(String name, int stock);

    void addReview(String name, String review);
}
