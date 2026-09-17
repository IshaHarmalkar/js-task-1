package org.example.config;

import org.example.controller.OrderController;
import org.example.controller.PaymentController;
import org.example.controller.ProductController;
import org.example.dao.OrderDao;
import org.example.dao.PaymentDao;
import org.example.dao.ProductDao;
import org.example.service.OrderService;
import org.example.service.PaymentService;
import org.example.service.ProductService;

public class AppFactory {

    private static final ProductDao productDao = new ProductDao();
    private static final OrderDao orderDao = new OrderDao();
    private static final PaymentDao paymentDao = new PaymentDao();

    private static final ProductService productService =
            new ProductService(productDao);

    private static final PaymentService paymentService =
            new PaymentService(paymentDao);

    private static final OrderService orderService =
            new OrderService(
                    productDao,
                    orderDao,
                    paymentService
            );

    private static final ProductController productController =
            new ProductController(productService);

    private static final OrderController orderController =
            new OrderController(orderService);

    private static final PaymentController paymentController =
            new PaymentController(paymentService);

    public static ProductController getProductController() {
        return productController;
    }

    public static OrderController getOrderController() {
        return orderController;
    }

    public static PaymentController getPaymentController() {
        return paymentController;
    }
}