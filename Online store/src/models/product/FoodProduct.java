package models.product;
import java.util.Date;

public class FoodProduct extends Product {
    private Date prodDate, expDate;

    public FoodProduct(String name, String description, double price, int stock, Date prodDate, Date expDate) {
        super(name, description, price, stock);
        this.prodDate = prodDate;
        this.expDate = expDate;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                super.toString() +
                "prodDate='" + prodDate + '\'' +
                ", expDate='" + expDate + '\'' +
                '}';
    }
}
