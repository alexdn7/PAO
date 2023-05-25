package repositories;

import models.product.ClothingProduct;
import models.product.ElectronicProduct;
import models.product.FoodProduct;
import models.product.Product;
import util.DBConnection;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DatabaseRepository implements IDatabaseRepository {

    @Override
    public Map<Integer, Product> getAllProducts() {
        Map<Integer, Product> products = new HashMap<>();
        try {
            Connection connection = DBConnection.getDbConnection();
            String query = "SELECT * FROM product p LEFT JOIN foodproduct fp on p.id = fp.id " +
                           "LEFT JOIN clothingproduct cp ON p.id = cp.id " +
                           "LEFT JOIN electronicproduct e on p.id = e.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");

                int warranty = resultSet.getInt("warranty");
                String size = resultSet.getString("size");
                Date prodDate = resultSet.getDate("prodDate");
                if(warranty != 0) {
                    String power = resultSet.getString("power");
                    products.put(id,new ElectronicProduct(name, description, price, stock, warranty, power));
                }

                if(size != null) {
                    products.put(id, new ClothingProduct(name, description, price, stock, size));
                }

                if(prodDate != null) {
                    products.put(id, new FoodProduct(name, description, price, stock, prodDate, resultSet.getDate("expDate")));
                }
            }

            resultSet.close();
            preparedStatement.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product) {
        try {
            Connection connection = DBConnection.getDbConnection();

            String query = "INSERT INTO PRODUCT (name, description, price, stock) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.execute();
            int generatedId = -1;
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

            if(product instanceof ElectronicProduct) {
                String query1 = "INSERT INTO ElectronicProduct (id, power, warranty) values (?, ?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, generatedId);
                preparedStatement1.setString(2, ((ElectronicProduct) product).getPower());
                preparedStatement1.setInt(3, ((ElectronicProduct) product).getWarranty());
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            if(product instanceof ClothingProduct) {
                String query1 = "INSERT INTO clothingproduct (id, size) values (?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, generatedId);
                preparedStatement1.setString(2, ((ClothingProduct) product).getSize());
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            if(product instanceof FoodProduct) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
