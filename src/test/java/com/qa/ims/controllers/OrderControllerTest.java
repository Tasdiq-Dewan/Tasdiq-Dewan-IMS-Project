package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private OrderDAO dao;
	
	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1L, 1L));
		
		Mockito.when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		Mockito.verify(this.dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testCreate() {
		final Long cId = 2L;
		final Order order = new Order(1L, cId);
		Mockito.when(utils.getLong()).thenReturn(cId);
		Mockito.when(dao.create(new Order(cId))).thenReturn(order);
		
		assertEquals(order, controller.create());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).create(new Order(cId));
	}
	
	@Test
	public void testUpdate() {
		final Long orderId = 1L, cId = 2L;
		final Order order = new Order(orderId, cId);
		
		Mockito.when(utils.getLong()).thenReturn(orderId, cId);
		
		Mockito.when(dao.update(order)).thenReturn(order);
		
		assertEquals(order, controller.update());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(order);
	}
	
	@Test
	public void testDelete() {
		final Long id = 1L;
		final int expected = 1;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(dao.delete(id)).thenReturn(expected);
		
		assertEquals(expected, controller.delete());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).delete(id);
	}
	
	@Test
	public void testAddItem() {
		final Long orderId = 1L, itemId = 3L;
		int expected = 1;
		Mockito.when(utils.getLong()).thenReturn(orderId, itemId);
		Mockito.when(dao.addItemToOrder(orderId, itemId)).thenReturn(1);
		
		assertEquals(expected, controller.addItem());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).addItemToOrder(orderId, itemId);
	}
}
