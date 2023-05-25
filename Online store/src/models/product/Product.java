package models.product;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    private String name, description;
    private double price;
    private int stock;
    private List<String> reviews = new ArrayList<>();

    public Product(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public double getPrice() {

        return price;
    }

    public int getStock() {

        return stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getReviews() {
        return reviews.toString();
    }
    public void addReview(String review) {
        this.reviews.add(review);
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", reviews=" + reviews.toString() + ", ";
    }
}

