package repositories;

import models.Costumer;
import models.enums.ProductType;
import models.product.ClothingProduct;
import models.product.ElectronicProduct;
import models.product.FoodProduct;
import models.product.Product;
import util.DBConnection;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DatabaseRepository implements IDatabaseRepository {

    private String loggedInUserEmail;

    @Override
    public Map<Integer, Product> getAllProducts() {
        Map<Integer, Product> products = new HashMap<>();
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "SELECT * FROM product p LEFT JOIN foodproduct fp on p.id = fp.id " +
                           "LEFT JOIN clothingproduct cp ON p.id = cp.id " +
                           "LEFT JOIN electronicproduct e on p.id = e.id " +
                           "LEFT JOIN review r on r.productId = p.id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String review = resultSet.getString("text");
                ProductType productType = ProductType.valueOf(resultSet.getString("type"));

                switch (productType) {
                    case Electronic -> {
                        int warranty = resultSet.getInt("warranty");
                        String power = resultSet.getString("power");
                        products.put(id, new ElectronicProduct(name, description, price, stock, warranty, power, resultSet.getString("type")));
                    }

                    case Clothing -> {
                        String size = resultSet.getString("size");
                        products.put(id, new ClothingProduct(name, description, price, stock, size, resultSet.getString("type")));
                    }

                    case Food -> {
                        Date prodDate = resultSet.getDate("prodDate");
                        products.put(id, new FoodProduct(name, description, price, stock, prodDate, resultSet.getDate("expDate"), resultSet.getString("type")));
                    }
                }
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "INSERT INTO PRODUCT (name, description, price, stock, type) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.setString(5, product.getType());
            preparedStatement.execute();

            int generatedId = -1;
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

            if (product instanceof ElectronicProduct) {
                String query1 = "INSERT INTO ElectronicProduct (id, power, warranty) values (?, ?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, generatedId);
                preparedStatement1.setString(2, ((ElectronicProduct) product).getPower());
                preparedStatement1.setInt(3, ((ElectronicProduct) product).getWarranty());
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            else if (product instanceof ClothingProduct) {
                String query1 = "INSERT INTO clothingproduct (id, size) values (?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, generatedId);
                preparedStatement1.setString(2, ((ClothingProduct) product).getSize());
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            else if (product instanceof FoodProduct) {
                String query1 = "INSERT INTO foodproduct (id, prodDate,expDate) values (?, ?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, generatedId);
                java.sql.Date prodDate = new java.sql.Date(((FoodProduct) product).getProdDate().getTime());
                preparedStatement1.setDate(2, prodDate);
                java.sql.Date expDate = new java.sql.Date(((FoodProduct) product).getExpDate().getTime());
                preparedStatement1.setDate(3, expDate);
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "SELECT type FROM product WHERE id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProductType productType = ProductType.valueOf(resultSet.getString("type"));

                switch (productType) {
                    case Electronic -> {
                        String electronicQuery = "DELETE FROM electronicproduct WHERE id = (?)";
                        preparedStatement = connection.prepareStatement(electronicQuery);
                        preparedStatement.setInt(1, id);
                        preparedStatement.execute();
                        System.out.println("Product with ID " + id + " was removed!");
                    }
                    case Clothing -> {
                        String clothingQuery = "DELETE FROM clothingproduct WHERE id = (?)";
                        preparedStatement = connection.prepareStatement(clothingQuery);
                        preparedStatement.setInt(1, id);
                        preparedStatement.execute();
                        System.out.println("Product with ID " + id + " was removed!");
                    }
                    case Food -> {
                        String foodQuery = "DELETE FROM foodproduct WHERE id = (?)";
                        preparedStatement = connection.prepareStatement(foodQuery);
                        preparedStatement.setInt(1, id);
                        preparedStatement.execute();
                        System.out.println("Product with ID " + id + " was removed!");
                    }
                }

                query = "DELETE FROM product WHERE id = (?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                preparedStatement.close();
            }
            preparedStatement.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createAccount(Costumer costumer) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "INSERT INTO costumer (name, address, phoneNumber, email, join_date) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, costumer.getName());
            preparedStatement.setString(2, costumer.getAddress());
            preparedStatement.setString(3, costumer.getPhoneNumber());
            preparedStatement.setString(4, costumer.getEmail());
            java.sql.Date joinDate = new java.sql.Date(new Date().getTime());
            preparedStatement.setDate(5, joinDate);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void login(String email){
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "SELECT * FROM costumer where email = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            try {
               resultSet.next();
               System.out.println("You logged in with success!");
               System.out.println("Welcome back, " + resultSet.getString("name") + "!");
               loggedInUserEmail = email;
            } catch (SQLException e) {
                throw new Exception("User doesn't exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeAddress(String address) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "UPDATE costumer SET address = (?) WHERE email = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, loggedInUserEmail);
            preparedStatement.execute();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public  void changePhoneNumber(String phoneNumber) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "UPDATE costumer SET phoneNumber = (?) WHERE email = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, loggedInUserEmail);
            preparedStatement.execute();
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount() {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "DELETE FROM costumer WHERE email = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loggedInUserEmail);
            preparedStatement.execute();
            preparedStatement.close();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<String> accountDetails() {
        List<String> accountDetailsList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "SELECT * FROM costumer where email = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loggedInUserEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                Date joinDate = resultSet.getDate("join_date");

                accountDetailsList.add(name);
                accountDetailsList.add(address);
                accountDetailsList.add(phoneNumber);
                accountDetailsList.add(email);
                accountDetailsList.add(joinDate.toString());
            }
            preparedStatement.close();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return accountDetailsList;
    }

    public boolean verifyProduct(int productId) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "SELECT * FROM product WHERE id = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return false;

    }

    public void addStock(int productId, int stock) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String updateQuery = "UPDATE product SET stock = (?) WHERE id = (?)";

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, stock);
            updateStatement.setInt(2, productId);
            updateStatement.executeUpdate();

            updateStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void addReview(int productId, String review) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String updateQuery = "UPDATE review SET text = (?) WHERE productId = (?)";

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, review);
            updateStatement.setInt(2, productId);
            updateStatement.executeUpdate();

            updateStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void modifyPrice(int productId, double price) {
        try {
            Connection connection = DBConnection.getDbConnection();
            String updateQuery = "UPDATE product SET price = (?) WHERE id = (?)";

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, price);
            updateStatement.setInt(2, productId);
            updateStatement.executeUpdate();

            updateStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
