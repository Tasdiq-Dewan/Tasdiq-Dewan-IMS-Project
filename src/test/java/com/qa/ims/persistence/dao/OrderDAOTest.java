package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testReadAll() {
		List<Item> expectedItems = new ArrayList<Item>();
		expectedItems.add(new Item(1L, "book", 10.00d));
		expectedItems.add(new Item(2L, "hat", 18.50d));
		Order order = new Order(1L, 1L, expectedItems);
		List<Order> expected = new ArrayList<Order>();
		expected.add(order);
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		List<Item> expectedItems = new ArrayList<Item>();
		expectedItems.add(new Item(1L, "book", 10.00d));
		expectedItems.add(new Item(2L, "hat", 18.50d));
		Order expected = new Order(1L, 1L, expectedItems);
		assertEquals(expected, DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		List<Item> expectedItems = new ArrayList<Item>();
		expectedItems.add(new Item(1L, "book", 10.00d));
		expectedItems.add(new Item(2L, "hat", 18.50d));
		Order expected = new Order(1L, 1L, expectedItems);
		assertEquals(expected, DAO.read(1L));
	}
	
	@Test
	public void testCreate() {
		Order expected = new Order(2L, 1L);
		assertEquals(expected, DAO.create(expected));
	}
	
	@Test
	public void testUpdate() {
		List<Item> expectedItems = new ArrayList<Item>();
		expectedItems.add(new Item(1L, "book", 10.00d));
		expectedItems.add(new Item(2L, "hat", 18.50d));
		Order expected = new Order(1L, 1L, expectedItems);
		assertEquals(expected, DAO.update(expected));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1L));
	}
	
	@Test
	public void testAddItem() {
		assertEquals(1, DAO.addItemToOrder(1L, 3L));
	}

	@Test
	public void testDeleteItem() {
		assertEquals(1, DAO.deleteItemFromOrder(1L, 2L));
	}
	
	@Test
	public void testCost() {
		assertEquals(28.50d, DAO.costOfOrder(1L), 0.001);
	}
	
}
