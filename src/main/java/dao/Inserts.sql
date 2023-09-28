/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  callumsullivan
 * Created: 27/09/2023
 */
-- Product 1: Laptop
INSERT INTO product (product_id, name, description, category, list_price, quantity_in_stock)
VALUES ('LPT001', 'High-Performance Laptop', 'Powerful laptop for work and gaming', 'Electronics', 999.99, 50);

-- Product 2: Smartphone
INSERT INTO product (product_id, name, description, category, list_price, quantity_in_stock)
VALUES ('PHN001', 'Flagship Smartphone', 'Top-tier smartphone with advanced features', 'Electronics', 799.99, 100);

-- Product 3: Smartwatch
INSERT INTO product (product_id, name, description, category, list_price, quantity_in_stock)
VALUES ('WTCH001', 'Smart Fitness Watch', 'Fitness tracker with heart rate monitor', 'Wearables', 149.99, 75);

-- Product 4: Gaming Console
INSERT INTO product (product_id, name, description, category, list_price, quantity_in_stock)
VALUES ('GC001', 'Next-Gen Gaming Console', 'Immersive gaming experience with 4K support', 'Gaming', 499.99, 30);

-- Product 5: Wireless Earbuds
INSERT INTO product (product_id, name, description, category, list_price, quantity_in_stock)
VALUES ('ERBD001', 'Wireless Noise-Canceling Earbuds', 'High-quality audio and noise cancellation', 'Audio', 199.99, 60);

-- Admin User with Password 'password'
INSERT INTO customer (username, first_name, surname, email_address, password)
VALUES ('admin', 'Admin', 'User', 'admin@example.com', 'password');