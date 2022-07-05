package com.qa.ims.persistence.domain;

import java.util.List;
import java.util.Objects;

public class Order {
	
	private Long orderId;
	private Long customerId;
	private List<Item> itemList;
	
	public Order(Long id, Long customer_id, List<Item> list) {
		super();
		this.orderId = id;
		this.customerId = customer_id; 
		this.itemList = list;
	}

	public Order(Long customer_id) {
		super();
		this.customerId = customer_id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "order id:" + orderId + ", customer id=" + customerId+"\n"+itemList.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, itemList, orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(itemList, other.itemList)
				&& Objects.equals(orderId, other.orderId);
	}

	
	
	
	
}
