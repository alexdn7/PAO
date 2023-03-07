import java.util.Arrays;

public class Car {
    private String name, color;
    private String[] reviews;

    public Car(String name, String color) {
        this.name = name;
        this.color = color;
        this.reviews = new String[0];
    }

    public String getName() {

        return this.name;
    }

    public String getColor() {

        return this.color;
    }

    public String getReviews() {
        return Arrays.deepToString(this.reviews);
    }

    public void addReview(String review) {

        String[] newArr = new String[this.reviews.length + 1];
        System.arraycopy(this.reviews, 0, newArr, 0, this.reviews.length);

        newArr[newArr.length - 1] = review;
        this.reviews = newArr;
    }
}
