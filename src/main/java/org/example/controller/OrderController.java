package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;

public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order createOrder(int productId, int quantity) {
        return orderService.createOrder(productId, quantity);
    }
}