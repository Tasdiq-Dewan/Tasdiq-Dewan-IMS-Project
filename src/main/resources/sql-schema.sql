drop schema ims;

CREATE SCHEMA IF NOT EXISTS ims;

USE ims ;

CREATE TABLE IF NOT EXISTS customers (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS items (
	`item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NOT NULL,
    `value` DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (`item_id`)
);


