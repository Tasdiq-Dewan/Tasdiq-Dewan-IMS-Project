package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {
	
	private Long orderId;
	private Long customerId;
	
	public Order(Long id, Long customer_id) {
		super();
		this.orderId = id;
		this.customerId = customer_id;
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

	@Override
	public String toString() {
		return "order id:" + orderId + ", customer id=" + customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, orderId);
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
		return Objects.equals(customerId, other.customerId) && Objects.equals(orderId, other.orderId);
	}
	
	
	
	
	
	
}
