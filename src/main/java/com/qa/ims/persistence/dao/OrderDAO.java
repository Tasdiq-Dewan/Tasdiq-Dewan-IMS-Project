package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	
	/**
	 * Creates an Order instance from result set
	 * Calls getOrderItems to create List of items associated with the order
	 * @return an order object
	 */
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Long customerId = resultSet.getLong("customer_id");
		List<Item> itemList = getOrderItems(orderId);
		return new Order(orderId, customerId, itemList);
	}
	
	/**
	 * Retrieves all items associated with an order by joining items table on itemorders association table 
	 * Takes in order_id for WHERE condition
	 * @return an arraylist of items created from the resultset of the query
	 */
	
	public List<Item> getOrderItems(Long id){
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT i.item_id, i.item_name, i.value "
						+ "FROM items i "
						+ "INNER JOIN itemorders ie ON ie.item_id = i.item_id "
						+ "WHERE ie.order_id = ?;");) {
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery();){
				List<Item> items = new ArrayList<Item>();
				while (resultSet.next()) {
					items.add(itemFromResultSet(resultSet));
				}
				return items;
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String itemName = resultSet.getString("item_name");
		float value = resultSet.getFloat("value");
		return new Item(id, itemName, value);
	}

	
	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a orders in the database
	 * 
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomerId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 * 
	 * @param order - takes in a order object, the id field will be used to
	 *                 update that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET customer_id = ? WHERE id = ?");) {
			statement.setLong(1, order.getCustomerId());
			statement.setLong(2, order.getOrderId());
			statement.executeUpdate();
			return read(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 
	 */
	public Order addItemToOrder(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO itemorders(order_id, item_id) VALUES (?, ?)");) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public int deleteItemFromOrder(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM itemorders WHERE order_id = ? AND item_id = ?");) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public float costOfOrder(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT SUM(i.value) AS sum "
						+ "FROM items i "
						+ "INNER JOIN itemorders ie ON ie.item_id = i.item_id "
						+ "WHERE ie.order_id = ?;");) {
			statement.setLong(1, orderId);
			try(ResultSet resultSet = statement.executeQuery();){
				resultSet.next();
				return resultSet.getFloat("sum");
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0f;
	}
}
