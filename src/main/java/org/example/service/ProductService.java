package org.example.service;

import org.example.dao.ProductDao;
import org.example.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(Product product) throws SQLException {
        productDao.insertProduct(product);
    }

    public Product getProduct(int id) {
        return productDao.selectProduct(id);
    }

    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public boolean updateProduct(Product product)
            throws SQLException {

        return productDao.updateProduct(product);
    }

    public boolean deleteProduct(int id)
            throws SQLException {

        return productDao.deleteProduct(id);
    }
}