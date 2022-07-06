package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order: orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please the id of the customer this order is for");
		Long customerId = utils.getLong();
		Order order = orderDAO.create(new Order(customerId));
		LOGGER.info("Order created");
		return order;
	}
	
	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the new customer id for this order");
		Long customerId = utils.getLong();
		Order order = orderDAO.update(new Order(customerId));
		LOGGER.info("Order Updated");
		return order;
	}
	
	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return int 
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

	
	/**
	 * Add an item to the order by inputting item id
	 * 
	 * @return int
	 */
	public int addItem() {
		LOGGER.info("Please enter the id of the order you would like to add an item to");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter the id of the item you would like to add");
		Long itemId = utils.getLong();
		return orderDAO.addItemToOrder(orderId, itemId);
	}
	
	/**
	 * Deleting an item from the order by inputting item id
	 * 
	 * @return int
	 */
	public int deleteItem() {
		LOGGER.info("Please enter the id of the order you would like to delete an item from");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter the id of the item you would like to delete from the order");
		Long itemId = utils.getLong();
		return orderDAO.deleteItemFromOrder(orderId, itemId);
	}


}
