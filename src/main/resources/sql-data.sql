USE ims;

INSERT INTO customers (first_name, surname) VALUES ('jordan', 'harrison');
INSERT INTO items(item_name, item_value) VALUES ("book", 10.00), ("hat", 18.50), ("pen", 1.00);
INSERT INTO orders(customer_id) VALUES (1);
INSERT INTO itemorders(order_id, item_id) VALUES (1, 1), (1, 2);

SELECT i.item_id, i.item_name, i.item_value 
FROM items i
INNER JOIN itemorders ie ON ie.item_id = i.item_id
WHERE ie.order_id = 1;

SELECT SUM(i.item_value)
FROM items i
INNER JOIN itemorders ie ON ie.item_id = i.item_id
WHERE ie.order_id = 1;