package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long id; //id for item
	private String itemName; //item name
	private double value; //value of the item
	
	
	//Constructor for creating Item after pulling data from database
	public Item(long id, String itemName, double value) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.value = value;
	}

	
	//Constructor without id to add a new row to items table
	public Item(String itemName, double value) {
		super();
		this.itemName = itemName;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "id:" + id + ", item name:" + itemName + ", value:" + value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemName, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	
	
	
	
	
}
