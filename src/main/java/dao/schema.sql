/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  callumsullivan
 * Created: 25/08/2023
 */
CREATE TABLE IF NOT EXISTS product (
    product_id VARCHAR(20),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    category VARCHAR(100) NOT NULL,
    list_price NUMERIC(10,2) NOT NULL,
    quantity_in_stock NUMERIC(10,2) NOT NULL,
    
    CONSTRAINT pk_productId PRIMARY KEY (product_id),
    CONSTRAINT chk_listPrice_non_negative CHECK(list_price >= 0),
    CONSTRAINT chk_quantityInStock_non_negative CHECK(quantity_in_stock >= 0)
);

CREATE TABLE IF NOT EXISTS Customer (
    customer_id INTEGER AUTO_INCREMENT (1000) NOT NULL,
    username VARCHAR(20) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    email_address VARCHAR(30) NOT NULL,
    shipping_address VARCHAR(200),
    password VARCHAR(20) NOT NULL,
    
    CONSTRAINT chk_username_length CHECK (CHAR_LENGTH(username) >= 2),
    CONSTRAINT chk_password_length CHECK (CHAR_LENGTH(password) >= 6),
    CONSTRAINT chk_valid_email CHECK (POSITION('@' IN email_address) > 0 AND POSITION('.' IN email_address) > POSITION('@' IN email_address)),
    CONSTRAINT pk_username PRIMARY KEY (username)
);

