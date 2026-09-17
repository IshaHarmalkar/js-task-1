<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Product Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="${pageContext.request.contextPath}/"
               class="navbar-brand">
                Product Management App
            </a>
        </div>
    </nav>
</header>

<br>

<div class="container">

    <h3 class="text-center">List of Products</h3>

    <hr>

    <div class="container text-left">
        <a href="${pageContext.request.contextPath}/new"
           class="btn btn-success">
            Add New Product
        </a>
    </div>

    <br>

    <table class="table table-bordered">

        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="product" items="${listProduct}">

            <tr>

                <td>
                    <c:out value="${product.id}" />
                </td>

                <td>
                    <c:out value="${product.name}" />
                </td>

                <td>
                    <c:out value="${product.price}" />
                </td>

                <td>
                    <c:out value="${product.category}" />
                </td>

                <td>
                    <c:out value="${product.quantity}" />
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/edit?id=<c:out value='${product.id}' />">
                        Edit
                    </a>

                    &nbsp;&nbsp;&nbsp;&nbsp;

                    <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${product.id}' />">
                        Delete
                    </a>
                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>
</html>