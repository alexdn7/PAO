package models;

import java.util.Map;

public class Costumer {
    private String name, address, phoneNumber, email;
    private final ShoppingCart shoppingCart;

    public Costumer(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        shoppingCart = new ShoppingCart();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String addres) {
        this.address = addres;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<String, Integer> getCartInfo() {
        return shoppingCart.getProducts();
    }

    public void addItemInCart(String name) {
        shoppingCart.addInCart(name);
    }

    @Override
    public String toString() {
        return "models.Costumer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
