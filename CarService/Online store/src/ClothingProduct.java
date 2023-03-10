public class ClothingProduct extends Product{
    private String size;

    public ClothingProduct(String name, String description, double price, int stock, String size) {
        super(name, description, price, stock);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
