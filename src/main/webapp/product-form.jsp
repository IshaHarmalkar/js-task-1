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

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <c:if test="${product != null}">
                <form action="${pageContext.request.contextPath}/update" method="post">
            </c:if>

            <c:if test="${product == null}">
                <form action="${pageContext.request.contextPath}/insert" method="post">
            </c:if>

                <caption>
                    <h2>
                        <c:if test="${product != null}">
                            Edit Product
                        </c:if>

                        <c:if test="${product == null}">
                            Add New Product
                        </c:if>
                    </h2>
                </caption>

                <c:if test="${product != null}">
                    <input type="hidden"
                           name="id"
                           value="<c:out value='${product.id}' />" />
                </c:if>

                <fieldset class="form-group">
                    <label>Product Name</label>

                    <input type="text"
                           value="<c:out value='${product.name}' />"
                           class="form-control"
                           name="name"
                           required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Price</label>

                    <input type="number"
                           step="0.01"
                           value="<c:out value='${product.price}' />"
                           class="form-control"
                           name="price"
                           required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Category</label>

                    <input type="text"
                           value="<c:out value='${product.category}' />"
                           class="form-control"
                           name="category"
                           required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Quantity</label>

                    <input type="number"
                           value="<c:out value='${product.quantity}' />"
                           class="form-control"
                           name="quantity"
                           required="required">
                </fieldset>

                <button type="submit" class="btn btn-success">
                    Save
                </button>

            </form>

        </div>
    </div>
</div>

</body>
</html>