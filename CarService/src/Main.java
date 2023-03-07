import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var carService = CarService.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your operation: ");
        System.out.println("0 - Exit from application: ");
        System.out.println("1 - List all cars: ");
        System.out.println("2 - Add a new car: ");
        System.out.println("3 - Add a new review to an existing car: ");
        var op = scanner.next();

        while(!op.equals("0")) {

            switch(op) {
                case "1" -> carService.listAllCars();
                case "2" -> {
                    System.out.println("Enter the name: ");
                    var temp = scanner.next();
                    System.out.println("Enter the color: ");
                    var temp2 = scanner.next();
                    carService.AddCar(new Car(temp, temp2));
                }
                case "3" -> {
                    System.out.println("Enter the name: ");
                    var temp = scanner.next();
                    System.out.println("Enter the review: ");
                    var temp2 = scanner.next();
                    carService.AddReview(temp, temp2);
                }
            }

            System.out.println("Enter the next operation: ");
            op = scanner.next();
        }
    }

}