package org.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.config.AppFactory;
import org.example.controller.OrderController;
import org.example.controller.ProductController;
import org.example.model.Order;
import org.example.model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet({
        "/",
        "/list",
        "/new",
        "/insert",
        "/delete",
        "/update",
        "/edit",
        "/order",
        "/place-order"
})
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductController productController;
    private OrderController orderController;

    @Override
    public void init() {

        productController =
                AppFactory.getProductController();

        orderController =
                AppFactory.getOrderController();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String action = request.getServletPath();

        try {

            switch (action) {

                // -------------------------
                // PRODUCT
                // -------------------------

                case "/new":
                    showNewForm(request, response);
                    break;

                case "/insert":
                    insertProduct(request, response);
                    break;

                case "/delete":
                    deleteProduct(request, response);
                    break;

                case "/update":
                    updateProduct(request, response);
                    break;

                case "/edit":
                    showEditForm(request, response);
                    break;

                // -------------------------
                // ORDER
                // -------------------------

                case "/order":
                    showOrderForm(request, response);
                    break;

                case "/place-order":
                    placeOrder(request, response);
                    break;

                // -------------------------
                // DEFAULT
                // -------------------------

                default:
                    listProduct(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // =========================================================
    // PRODUCT METHODS
    // =========================================================

    private void listProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<Product> listProduct =
                productController.getAllProducts();

        request.setAttribute(
                "listProduct",
                listProduct
        );

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "product-list.jsp"
                );

        dispatcher.forward(request, response);
    }

    private void showNewForm(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "product-form.jsp"
                );

        dispatcher.forward(request, response);
    }

    private void showEditForm(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        Product existingProduct =
                productController.getProduct(id);

        request.setAttribute(
                "product",
                existingProduct
        );

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "product-form.jsp"
                );

        dispatcher.forward(request, response);
    }

    private void insertProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws SQLException, IOException {

        String name =
                request.getParameter("name");

        float price =
                Float.parseFloat(
                        request.getParameter("price")
                );

        String category =
                request.getParameter("category");

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity")
                );

        Product newProduct =
                new Product(
                        name,
                        price,
                        category,
                        quantity
                );

        productController.addProduct(newProduct);

        response.sendRedirect("list");
    }

    private void updateProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws SQLException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        String name =
                request.getParameter("name");

        float price =
                Float.parseFloat(
                        request.getParameter("price")
                );

        String category =
                request.getParameter("category");

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity")
                );

        Product product =
                new Product(
                        id,
                        name,
                        price,
                        category,
                        quantity
                );

        productController.updateProduct(product);

        response.sendRedirect("list");
    }

    private void deleteProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws SQLException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        productController.deleteProduct(id);

        response.sendRedirect("list");
    }

    // =========================================================
    // ORDER METHODS
    // =========================================================

    private void showOrderForm(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        int productId =
                Integer.parseInt(
                        request.getParameter("id")
                );

        Product product =
                productController.getProduct(productId);

        request.setAttribute(
                "product",
                product
        );

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "order-form.jsp"
                );

        dispatcher.forward(request, response);
    }

    private void placeOrder(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        int productId =
                Integer.parseInt(
                        request.getParameter("productId")
                );

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity")
                );

        Order order =
                orderController.createOrder(
                        productId,
                        quantity
                );

        if (order == null) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Unable to create order"
            );

            return;
        }

        request.setAttribute("order", order);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "order-success.jsp"
                );

        dispatcher.forward(request, response);
    }
}