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

}
