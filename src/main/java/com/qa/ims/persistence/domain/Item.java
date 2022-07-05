package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long id;
	private String itemName;
	private float value;
	
	public Item(long id, String itemName, float value) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.value = value;
	}

	public Item(String itemName, float value) {
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

	public float getValue() {
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
		return id == other.id && Objects.equals(itemName, other.itemName)
				&& Float.floatToIntBits(value) == Float.floatToIntBits(other.value);
	}
	
	
	
	
	
}
