package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Product product) throws SQLException {
        productService.addProduct(product);
    }

    public Product getProduct(int id) {
        return productService.getProduct(id);
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public boolean updateProduct(Product product) throws SQLException {
        return productService.updateProduct(product);
    }

    public boolean deleteProduct(int id) throws SQLException {
        return productService.deleteProduct(id);
    }
}