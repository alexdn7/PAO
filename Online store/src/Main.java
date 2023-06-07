import models.Costumer;
import models.product.ClothingProduct;
import models.product.ElectronicProduct;
import models.product.FoodProduct;
import repositories.DatabaseRepository;
import repositories.IDatabaseRepository;
import services.AccountService;
import services.StoreService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        IDatabaseRepository databaseRepository = new DatabaseRepository();
        var storeService = StoreService.getInstance(databaseRepository);
        var accountService = AccountService.getInstance(databaseRepository);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our online store!");
        System.out.println("Do you have an account?: Y / N");
        var temp = scanner.next();
        String name;

        switch (temp) {
            case "N" -> {
                System.out.println("Do you want to create an account?: Y / N");
                var answer = scanner.next();

                switch (answer) {
                    case "y", "Y" -> {
                        System.out.println("Enter your name: ");
                        name = scanner.nextLine();
                        scanner.next();
                        System.out.println("Enter your address: ");
                        var address = scanner.nextLine();
                        scanner.next();
                        System.out.println("Enter your phone number: ");
                        var phoneNumber = scanner.next();
                        System.out.println("Enter your email: ");
                        var email = scanner.next();
                        accountService.createAccount(new Costumer(name, address, phoneNumber, email));
                        System.out.println("Account created!");
                    }

                    case "n", "N" -> System.exit(0);
                }
            }

            case "Y" -> {
                System.out.println("Enter your email");
                String email = scanner.next();
                accountService.login(email);
            }
            default -> System.exit(0);
        }

        System.out.println("\nIf you want to exit, type '0'");
        System.out.println("Else, type anything else: ");
        var status = scanner.next();

        if(status.equals("0"))
            System.exit(0);

        System.out.println();

        while(!status.equals("0")) {

            System.out.println("Select your operation: ");
            System.out.println("1. List all products");
            System.out.println("2. Add a product");
            System.out.println("3. Add review to a product");
            System.out.println("4. Change the stock of a product");
            System.out.println("5. Delete my account");
            System.out.println("6. Change the price of a product");
            System.out.println("7. List all products, sorted by name, alphabetically");
            System.out.println("8. List all products, sorted by price");
            System.out.println("9. Print my account details");
            System.out.println("10. Change my phone number");
            System.out.println("11. Change my address");
            System.out.println("12. Remove a product by productId");

            var operation = scanner.next();

            switch (operation) {
                case "1" -> storeService.listAllProducts();

                case "2" -> {
                    System.out.println("Enter product type: (Electronic, clothing, food) ");
                    var productType = scanner.next();
                    System.out.println("Enter product name: ");
                    var productName = scanner.next();
                    System.out.println("Enter product description: ");
                    var productDescription = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter product price: ");
                    var productPrice = scanner.nextDouble();
                    System.out.println("Enter stock: ");
                    var stock = scanner.nextInt();
                    if (productType.equalsIgnoreCase("Electronic")) {
                        System.out.println("Enter warranty (no. of months): ");
                        var warranty = scanner.nextInt();
                        System.out.println("Power: ");
                        var power = scanner.next();
                        storeService.addProduct(new ElectronicProduct(productName, productDescription, productPrice, stock, warranty, power, "Electronic"));
                        System.out.println("models.product.Product added!");
                    }

                    else if(productType.equalsIgnoreCase("food")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        System.out.println("Date of manufacture (DD-MM-YYYY): ");
                        var prodDate = scanner.next();
                        System.out.println("Expiration date (DD-MM-YYYY): ");
                        var expDate = scanner.next();
                        try {
                            storeService.addProduct(new FoodProduct(productName, productDescription, productPrice, stock, dateFormat.parse(prodDate), dateFormat.parse(expDate),  "Food"));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("models.product.Product added!");
                    }

                    else {
                        System.out.println("Size (XS/S/M/L/XL/XXL): ");
                        var size = scanner.next();
                        storeService.addProduct(new ClothingProduct(productName, productDescription, productPrice, stock, size, "Clothing"));
                    }
                }

                case "3" -> {
                    System.out.println("Enter product id: ");
                    var productId = scanner.nextInt();
                    if (!storeService.verifyProduct(productId))
                        System.out.println("This product does not exist!");
                    else {
                        System.out.println("Enter the review: ");
                        var review = scanner.nextLine();
                        storeService.addReview(productId, review);
                        System.out.println("Added!");
                    }
                }

                case "4" -> {
                    System.out.println("Enter product id: ");
                    var productId = scanner.nextInt();
                    if (!storeService.verifyProduct(productId))
                        System.out.println("This product does not exist!");
                    else {
                        System.out.println("New value: ");
                        var newStock = scanner.nextInt();
                        storeService.addStock(productId, newStock);
                        System.out.println("Added!");
                    }
                }

                case "5" -> {
                    System.out.println("Are your sure? (YES / NO)");
                    var answer = scanner.next();
                    if(answer.equalsIgnoreCase("YES")) {
                        accountService.deleteAccount();
                    }
                    else {
                        System.out.println("Don't press this button again!");
                    }
                }

                case "6" -> {
                    System.out.println("Enter productId: ");
                    var productId = scanner.nextInt();
                    if (!storeService.verifyProduct(productId))
                        System.out.println("This product does not exist!");
                    else {
                        System.out.println("New value: ");
                        var newPrice = scanner.nextDouble();
                        storeService.modifyPrice(productId, newPrice);
                        System.out.println("Changed!");
                    }
                }

                case "7" -> storeService.listAllProductsSortedByName();

                case "8" -> storeService.listAllProductsSortedByPrice();

                case "9" -> System.out.println(accountService.accountDetails());

                case "10" -> {
                    System.out.println("New phone number: ");
                    var newPhoneNumber = scanner.next();
                    accountService.changePhoneNumber(newPhoneNumber);
                }

                case "11" -> {
                    System.out.println("New address: ");
                    var newAddress = scanner.next();
                    accountService.changeAddress(newAddress);
                }

                case "12" -> {
                    System.out.println("Insert the id");
                    var productId = scanner.nextInt();
                    storeService.deleteProductById(productId);
                }
            }

            System.out.println("If you want to exit, type '0'");
            System.out.println("Else, type anything else: ");
            status = scanner.next();
            }

    }
}