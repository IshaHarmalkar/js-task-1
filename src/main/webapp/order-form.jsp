<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
</head>

<body>

<h1>Place Order</h1>

<p>
    <strong>Product:</strong>
    ${product.name}
</p>

<p>
    <strong>Category:</strong>
    ${product.category}
</p>

<p>
    <strong>Price:</strong>
    ₹${product.price}
</p>

<p>
    <strong>Available Quantity:</strong>
    ${product.quantity}
</p>

<hr>

<form
        action="${pageContext.request.contextPath}/place-order"
        method="post">

    <input
            type="hidden"
            name="productId"
            value="${product.id}"
    >

    <label for="quantity">
        Quantity:
    </label>

    <input
            type="number"
            id="quantity"
            name="quantity"
            min="1"
            max="${product.quantity}"
            required
    >

    <br><br>

    <button type="submit">
        Place Order
    </button>

</form>

<br>

<a href="${pageContext.request.contextPath}/list">
    Back to Products
</a>

</body>

</html>