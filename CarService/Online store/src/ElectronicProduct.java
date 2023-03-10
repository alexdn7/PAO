public class ElectronicProduct extends Product{
    private String power;
    private int warranty;

    public ElectronicProduct(String name, String description, double price, int stock, int warranty, String power) {
        super(name, description, price, stock);
        this.warranty = warranty;
        this.power = power;
    }

    public int getWarranty() {
        return warranty;
    }

    public String getPower() {
        return power;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
