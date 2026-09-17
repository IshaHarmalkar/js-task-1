package org.example.dao;

import org.example.model.Payment;
import org.example.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao {

    private static final String INSERT_PAYMENT_SQL =
            "INSERT INTO payment " +
                    "(order_id, amount, payment_method, status) " +
                    "VALUES (?, ?, ?, ?);";


    public int insertPayment(Payment payment)
            throws SQLException {

        int paymentId = 0;

        try (
                Connection connection = DbUtil.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                INSERT_PAYMENT_SQL,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )
        ) {

            preparedStatement.setInt(
                    1,
                    payment.getOrder().getId()
            );

            preparedStatement.setFloat(
                    2,
                    payment.getAmount()
            );

            preparedStatement.setString(
                    3,
                    payment.getPaymentMethod()
            );

            preparedStatement.setString(
                    4,
                    payment.getStatus()
            );


            preparedStatement.executeUpdate();


            ResultSet rs =
                    preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                paymentId = rs.getInt(1);
            }
        }

        return paymentId;
    }
}