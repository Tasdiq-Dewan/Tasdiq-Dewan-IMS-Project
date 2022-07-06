

CREATE TABLE IF NOT EXISTS customers (
    id INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    surname VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
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
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS itemorders(
	id INT(11) NOT NULL AUTO_INCREMENT, 
    order_id INT(11) NOT NULL,
    item_id INT(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE
);