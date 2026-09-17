CREATE DATABASE IF NOT EXISTS productDb1;
USE productDb1;


CREATE  TABLE  IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL,
    category VARCHAR(100) NOT NUll,
    quantity INT NOT NULL

);

CREATE TABLE IF NOT EXISTS orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  total_amount FLOAT NOT NULL,
  status VARCHAR(50) NOT NULL,

  CONSTRAINT fk_order_product
      FOREIGN KEY (product_id)
          REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS payment (
   id INT AUTO_INCREMENT PRIMARY KEY,
   order_id INT NOT NULL,
   amount FLOAT NOT NULL,
   payment_method VARCHAR(50) NOT NULL,
   status VARCHAR(50) NOT NULL,
   CONSTRAINT fk_payment_order
       FOREIGN KEY (order_id)
           REFERENCES orders(id)
);



INSERT INTO product (name, price, category, quantity)
VALUES
    ('Laptop', 1500, 'Electronics', 10),
    ('Mouse', 500, 'Electronics', 20),
    ('Keyboard', 1000, 'Electronics', 15),
    ('Headphones', 1200, 'Audio', 8);