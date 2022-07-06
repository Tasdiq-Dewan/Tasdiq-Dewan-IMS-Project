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
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the new customer id for this order");
		Long customerId = utils.getLong();
		Order order = orderDAO(new Order(customerId));
		LOGGER.info("Order Updated");
		return order;
	}



}
