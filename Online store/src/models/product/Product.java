package models.product;

public abstract class Product {

    private String name, description;
    private double price;
    private int stock;
    private String review = "";
    private final String type;

    public Product(String name, String description, double price, int stock, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public Product(String name, String description, double price, int stock, String type, String review) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.review = review;
    }

    public String getName() {

        return name;
    }

    public String getType() {
        return type;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", review=" + review + ", ";
    }
}

