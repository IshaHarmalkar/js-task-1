<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Order Successful</title>
</head>

<body>

<h1>Order Successful</h1>

<p>
    Your order has been created successfully.
</p>

<hr>

<h2>Order Details</h2>

<p>
    <strong>Order ID:</strong>
    ${order.id}
</p>

<p>
    <strong>Product:</strong>
    ${order.product.name}
</p>

<p>
    <strong>Quantity:</strong>
    ${order.quantity}
</p>

<p>
    <strong>Total Amount:</strong>
    ₹${order.totalAmount}
</p>

<p>
    <strong>Status:</strong>
    ${order.status}
</p>

<hr>

<h2>Payment</h2>

<p>
    Payment processed successfully.
</p>

<p>
    <strong>Payment Method:</strong>
    CARD
</p>

<p>
    <strong>Payment Status:</strong>
    SUCCESS
</p>

<br>

<a href="${pageContext.request.contextPath}/list">
    Back to Products
</a>

</body>

</html>