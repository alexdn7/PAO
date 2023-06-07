package repositories;

import models.Costumer;
import models.product.Product;

import java.util.List;
import java.util.Map;

public interface IDatabaseRepository {

    Map<Integer, Product> getAllProducts();

    void addProduct(Product product);

    void deleteProduct(int id);

    void createAccount(Costumer costumer);

    void login(String email);

    void changeAddress(String address);

    void changePhoneNumber(String phoneNumber);

    void deleteAccount();

    List<String> accountDetails();

    boolean verifyProduct(int productId);

    void addStock(int productId, int stock);

    void addReview(int productId, String review);

    void modifyPrice(int productId, double price);
}
