package org.example.controller;

import org.example.model.Order;
import org.example.model.Payment;
import org.example.service.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Payment processPayment(Order order) {
        return paymentService.processPayment(order);
    }
}
