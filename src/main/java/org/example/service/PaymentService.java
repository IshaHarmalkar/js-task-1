package org.example.service;

import org.example.dao.PaymentDao;
import org.example.model.Order;
import org.example.model.Payment;

import java.sql.SQLException;

public class PaymentService {

    private PaymentDao paymentDao;


    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }


    public Payment processPayment(Order order) {

        try {

            Payment payment = new Payment();

            payment.setOrder(order);
            payment.setAmount(order.getTotalAmount());
            payment.setPaymentMethod("CARD");
            payment.setStatus("SUCCESS");


            int paymentId =
                    paymentDao.insertPayment(payment);

            payment.setId(paymentId);


            System.out.println(
                    "Payment of ₹" +
                            payment.getAmount() +
                            " processed successfully"
            );


            return payment;

        } catch (SQLException e) {

            e.printStackTrace();

            return null;
        }
    }
}