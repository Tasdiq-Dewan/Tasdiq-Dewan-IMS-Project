drop schema ims;

CREATE SCHEMA IF NOT EXISTS ims;

USE ims ;

CREATE TABLE IF NOT EXISTS customers (
    customer_id INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    surname VARCHAR(40) NOT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS items (
	item_id INT(11) NOT NULL AUTO_INCREMENT,
    item_name VARCHAR(40) NOT NULL,
    item_value DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS orders(
	order_id INT(11) NOT NULL AUTO_INCREMENT,
    customer_id INT(11) NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS customerorders(
	id INT(11) NOT NULL AUTO_INCREMENT,
    customer_id INT(11) NOT NULL,
    order_id INT(11) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);
CREATE TABLE IF NOT EXISTS itemorders(
	id INT(11) NOT NULL AUTO_INCREMENT,
    item_id INT(11) NOT NULL,
    order_id INT(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);



