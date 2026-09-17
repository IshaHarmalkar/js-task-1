package org.example.dao;

import org.example.model.Product;
import org.example.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String INSERT_PRODUCT_SQL =
            "INSERT INTO product (name, price, category, quantity) " +
                    "VALUES (?, ?, ?, ?);";

    private static final String SELECT_PRODUCT_BY_ID =
            "SELECT id, name, price, category, quantity " +
                    "FROM product WHERE id = ?;";

    private static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM product";

    private static final String DELETE_PRODUCT_SQL =
            "DELETE FROM product WHERE id = ?;";

    private static final String UPDATE_PRODUCT_SQL =
            "UPDATE product " +
                    "SET name = ?, price = ?, category = ?, quantity = ? " +
                    "WHERE id = ?;";

    private static final String UPDATE_PRODUCT_QUANTITY_SQL =
            "UPDATE product " +
                    "SET quantity = quantity - ? " +
                    "WHERE id = ?;";


    public void insertProduct(Product product) throws SQLException {

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(INSERT_PRODUCT_SQL)
        ) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setInt(4, product.getQuantity());

            preparedStatement.executeUpdate();
        }
    }


    public Product selectProduct(int id) {

        Product product = null;

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(SELECT_PRODUCT_BY_ID)
        ) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");

                product = new Product(
                        id,
                        name,
                        price,
                        category,
                        quantity
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }


    public List<Product> selectAllProducts() {

        List<Product> products = new ArrayList<>();

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(SELECT_ALL_PRODUCTS)
        ) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");

                products.add(
                        new Product(
                                id,
                                name,
                                price,
                                category,
                                quantity
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public boolean deleteProduct(int id) throws SQLException {

        boolean rowDeleted;

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(DELETE_PRODUCT_SQL)
        ) {

            statement.setInt(1, id);

            rowDeleted = statement.executeUpdate() > 0;
        }

        return rowDeleted;
    }


    public boolean updateProduct(Product product) throws SQLException {

        boolean rowUpdated;

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(UPDATE_PRODUCT_SQL)
        ) {

            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }


    public boolean updateProductQuantity(
            int productId,
            int quantity
    ) throws SQLException {

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(
                                UPDATE_PRODUCT_QUANTITY_SQL
                        )
        ) {

            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            return statement.executeUpdate() > 0;
        }
    }
}