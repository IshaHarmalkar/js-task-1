package org.example.servlet;

import com.mysql.cj.PreparedQuery;
import com.mysql.cj.protocol.a.SqlDateValueEncoder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.ProductDao;
import org.example.model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet({"/", "/list", "/new", "/insert", "/delete", "/update", "/edit"})
public class ProductServlet extends HttpServlet {

    private static  final long serialVersionUID = 1L;

    private ProductDao productDao;

    public void init(){
        productDao = new ProductDao();
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try{
            switch (action){
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
                    updateProduct(request,response);
                    break;

                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }


    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        List<Product> listProduct = productDao.selectAllProducts();

        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");


        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");

        dispatcher.forward(request, response);

    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws  SQLException, ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));

        Product existingProduct = productDao.selectProduct(id);

        request.setAttribute("product", existingProduct);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");

        dispatcher.forward(request, response);

    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product newProduct = new Product(name, price, category, quantity);

        productDao.insertProduct(newProduct);
        response.sendRedirect("list");
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product product = new Product(id, name, price, category, quantity);
        productDao.updateProduct(product);

        response.sendRedirect("list");
    }


    private  void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws
         SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDao.deleteProduct(id);
        response.sendRedirect("list");
    }

}
