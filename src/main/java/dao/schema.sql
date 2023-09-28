DROP TABLE IF EXISTS sale_item;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS product;


CREATE TABLE IF NOT EXISTS product (
    product_id VARCHAR(20),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    category VARCHAR(100) NOT NULL,
    list_price NUMERIC(10,2) NOT NULL,
    quantity_in_stock INT NOT NULL,
    
    CONSTRAINT pk_productId PRIMARY KEY (product_id),
    CONSTRAINT chk_listPrice_non_negative CHECK(list_price >= 0),
    CONSTRAINT chk_quantityInStock_non_negative CHECK(quantity_in_stock >= 0)
);

CREATE TABLE IF NOT EXISTS customer (
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

CREATE TABLE IF NOT EXISTS sale (
    sale_id INT NOT NULL AUTO_INCREMENT,
    customer_username VARCHAR(20) NOT NULL,
    sale_date DATETIME,
    sale_status VARCHAR(255),
    CONSTRAINT pk_sale_ID PRIMARY KEY (sale_id),
    CONSTRAINT fk_username FOREIGN KEY (customer_username) REFERENCES customer (username)
);

CREATE TABLE IF NOT EXISTS sale_item (
    sale_item_id INT NOT NULL AUTO_INCREMENT,
    sale_id INT NOT NULL,
    product_id VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    sale_price DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_sale__item_id PRIMARY KEY (sale_item_id),
    CONSTRAINT fk_sale FOREIGN KEY (sale_id) REFERENCES sale (sale_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);


