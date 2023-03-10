import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var storeService = StoreService.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our online store!");
        System.out.println("Do you have an account?: Y / N");
        var temp = scanner.next();
        String name = "";

        switch (temp) {
            case "N" -> {
                System.out.println("Do you want to create an account?: Y / N");
                var answer = scanner.next();

                switch (answer) {
                    case "y", "Y" -> {
                        System.out.println("Enter your name: ");
                        name = scanner.next();
                        System.out.println("Enter your address: ");
                        var address = scanner.next();
                        System.out.println("Enter your phone number: ");
                        var phoneNumber = scanner.next();
                        System.out.println("Enter your email: ");
                        var email = scanner.next();
                        storeService.addCostumer(new Costumer(name, address, phoneNumber, email));
                        System.out.println("Account created!");
                    }

                    case "n", "N" -> System.exit(0);
                }
            }

            case "Y" -> {
                System.out.println("Enter your name");
                name = scanner.next();
                if (!storeService.verify(name)) {
                    System.out.println("Wrong name!");
                    System.exit(0);
                }
            }
            default -> System.exit(0);
        }

        System.out.println("\nIf you want to exit, type '0'");
        System.out.println("Else, type anything else: ");
        var status = scanner.next();

        if(status.equals("0"))
            System.exit(0);

        else {
                System.out.println("Enter your name");
                name = scanner.next();
                if (!storeService.verify(name)) {
                    System.out.println("Wrong name!");
                    System.exit(0);
                }
        }

        System.out.println();

        while(!status.equals("0")) {

            System.out.println("Select your operation: ");
            System.out.println("1. List all products");
            System.out.println("2. Add a product");
            System.out.println("3. Add review to a product");
            System.out.println("4. Change the stock of a product");
            System.out.println("5. List all costumers");
            System.out.println("6. Change the price of a product");
            System.out.println("7. Add an item in my shopping cart");
            System.out.println("8. View items from my cart");
            System.out.println("9. Change my account email");
            System.out.println("10. Change my phone number");
            System.out.println("11. Change my address");
            var operation = scanner.next();

            switch (operation) {
                case "1" -> storeService.listAllProducts();
                case "2" -> {
                    System.out.println("Enter product type: (Electrical, clothing, food) ");
                    var productType = scanner.next();
                    System.out.println("Enter product name: ");
                    var productName = scanner.next();
                    System.out.println("Enter product description: ");
                    var productDescription = scanner.next();
                    System.out.println("Enter product price: ");
                    var productPrice = scanner.nextDouble();
                    System.out.println("Enter stock: ");
                    var stock = scanner.nextInt();
                    if (productType.equalsIgnoreCase("Electrical")) {
                        System.out.println("Enter warranty (no. of months): ");
                        var warranty = scanner.nextInt();
                        System.out.println("Power: ");
                        var power = scanner.next();
                        storeService.addProduct(new ElectronicProduct(productName, productDescription, productPrice, stock, warranty, power));
                        System.out.println("Product added!");
                    }

                    else if(productType.equalsIgnoreCase("food")) {
                        System.out.println("Date of manufacture (DD-MM-YYYY): ");
                        var prodDate = scanner.next();
                        System.out.println("Expiration date (DD-MM-YYYY): ");
                        var expDate = scanner.next();
                        storeService.addProduct(new FoodProduct(productName, productDescription, productPrice, stock, prodDate, expDate));
                        System.out.println("Product added!");
                    }

                    else {
                        System.out.println("Size (XS/S/M/L/XL/XXL): ");
                        var size = scanner.next();
                        storeService.addProduct(new ClothingProduct(productName, productDescription, productPrice, stock, size));
                    }
                }
                case "3" -> {
                    System.out.println("Enter product name: ");
                    var prodName = scanner.next();
                    if(!storeService.verifyProduct(prodName))
                        System.out.println("This product does not exist!");
                    else {
                        System.out.println("Enter review: ");
                        var review = scanner.next();
                        storeService.addReview(prodName, review);
                        System.out.println("Review added!");
                    }
                }
                case "4" -> {
                    System.out.println("Enter product name: ");
                    var prodName = scanner.next();
                    if(!storeService.verifyProduct(prodName))
                        System.out.println("This product does not exist!");
                    else {
                        System.out.println("New value: ");
                        var newStock = scanner.nextInt();
                        storeService.addStock(prodName, newStock);
                        System.out.println("Added!");
                    }
                }
                case "5" -> storeService.listAllCostumers();
                case "6" -> {
                    System.out.println("Enter product name: ");
                    var prodName = scanner.next();
                    if(!storeService.verifyProduct(prodName))
                        System.out.println("This product does not exist!");
                    else {
                        System.out.println("New value: ");
                        var newPrice = scanner.nextInt();
                        storeService.modifyPrice(prodName, newPrice);
                        System.out.println("Changed!");
                    }
                }
                case "7" -> {
                    System.out.println("Product name: ");
                    var prodName = scanner.next();
                    storeService.addItemInCart(name, prodName);
                }
                case "8" -> storeService.printCartInfo(name);
                case "9" -> {
                    System.out.println("New email: ");
                    var newEmail = scanner.next();
                    storeService.modifyEmail(name, newEmail);
                }
                case "10" -> {
                    System.out.println("New phone number: ");
                    var newPhoneNumber = scanner.next();
                    storeService.modifyPhoneNumber(name, newPhoneNumber);
                }
                case "11" -> {
                    System.out.println("New address: ");
                    var newAddress = scanner.next();
                    storeService.modifyAddress(name, newAddress);
                }
            }

            System.out.println("If you want to exit, type '0'");
            System.out.println("Else, type anything else: ");
            status = scanner.next();
            }
    }
}