package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(4L, "box", 5.00d);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<Item>();
		expected.add(new Item(1L, "book", 10.00d));
		expected.add(new Item(2L, "hat", 18.50d));
		expected.add(new Item(3L, "pen", 1.00d));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		Item expected = new Item(3L, "pen", 1.00d);
		assertEquals(expected, DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		Item expected = new Item(1L, "book", 10.00d);
		assertEquals(expected, DAO.read(1L));
	}
	
	@Test
	public void testUpdate() {
		Item expected = new Item(1L, "textbook", 50.00d);
		assertEquals(expected, DAO.update(expected));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1L));
	}
}
