package models.product;

import models.product.Product;

public class ClothingProduct extends Product {
    private String size;

    public ClothingProduct(String name, String description, double price, int stock, String size, String type) {
        super(name, description, price, stock, type);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ClothingProduct{" +
                super.toString() +
                "size='" + size + '\'' +
                '}';
    }
}
