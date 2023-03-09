public class FoodProduct extends Product {
    private String prodDate, expDate;

    public FoodProduct(String name, String description, double price, int stock, String prodDate, String expDate) {
        super(name, description, price, stock);
        this.prodDate = prodDate;
        this.expDate = expDate;
    }

    public String getProdDate() {
        return prodDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setProdDate(String prodDate) {
        this.prodDate = prodDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
