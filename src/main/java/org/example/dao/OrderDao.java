package org.example.dao;

import org.example.model.Order;
import org.example.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {

    private static final String INSERT_ORDER_SQL =
            "INSERT INTO orders " +
                    "(product_id, quantity, total_amount, status) " +
                    "VALUES (?, ?, ?, ?);";


    public int insertOrder(Order order) throws SQLException {

        int orderId = 0;

        try (
                Connection connection = DbUtil.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                INSERT_ORDER_SQL,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )
        ) {

            preparedStatement.setInt(
                    1,
                    order.getProduct().getId()
            );

            preparedStatement.setInt(
                    2,
                    order.getQuantity()
            );

            preparedStatement.setFloat(
                    3,
                    order.getTotalAmount()
            );

            preparedStatement.setString(
                    4,
                    order.getStatus()
            );

            preparedStatement.executeUpdate();

            ResultSet rs =
                    preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        }

        return orderId;
    }
}