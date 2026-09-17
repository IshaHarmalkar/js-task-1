package org.example.service;

import org.example.dao.OrderDao;
import org.example.dao.ProductDao;
import org.example.model.Order;
import org.example.model.Product;

public class OrderService {

    private ProductDao productDao;
    private OrderDao orderDao;
    private PaymentService paymentService;


    public OrderService(
            ProductDao productDao,
            OrderDao orderDao,
            PaymentService paymentService
    ) {
        this.productDao = productDao;
        this.orderDao = orderDao;
        this.paymentService = paymentService;
    }


    public Order createOrder(
            int productId,
            int quantity
    ) {

        try {

            // 1. Get product
            Product product =
                    productDao.selectProduct(productId);

            if (product == null) {

                System.out.println(
                        "Product not found."
                );

                return null;
            }


            // 2. Check quantity
            if (product.getQuantity() < quantity) {

                System.out.println(
                        "Insufficient product quantity."
                );

                return null;
            }


            // 3. Calculate total
            float totalAmount =
                    product.getPrice() * quantity;


            // 4. Create Order object
            Order order = new Order();

            order.setProduct(product);
            order.setQuantity(quantity);
            order.setTotalAmount(totalAmount);
            order.setStatus("CREATED");


            // 5. Save order
            int orderId =
                    orderDao.insertOrder(order);

            order.setId(orderId);


            // 6. Reduce product quantity
            productDao.updateProductQuantity(
                    productId,
                    quantity
            );


            // 7. Process payment
            paymentService.processPayment(order);


            return order;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}