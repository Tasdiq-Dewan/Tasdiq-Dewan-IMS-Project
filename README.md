# Software Core Fundamental IMS Project

Inventory Management System project for QA Acadamy to demonstrate concepts in Agile & Project Management, Databases, and Programming Fundamentals.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Have the latest [Java SE Development Kit version 17 installed.](https://www.oracle.com/java/technologies/downloads/#java17)

Have the latest [MySQL Community Server version 8 installed including MySQL Workbench.](https://dev.mysql.com/downloads/windows/installer/8.0.html)

Have the latest [Apache Maven version installed.](https://maven.apache.org/download.cgi)


### Installing

Step 1: Clone the repository to your local device.

Step 2: Go into Tasdiq-Dewan-IMS-Project\src\main\resources and open sql-schema.sql and sql-data.sql in MySQL Workbench and then run them on your local instance

Step 3: Open Tasdiq-Dewan-IMS-Project\src\main\resources\db.properties and edit the port, username, and password to match those of your local MySQL instance.

### Running in command prompt

Step 1: Open command prompt at Tasdiq-Dewan-IMS-Project

Step 2: Run the command 

```
java -jar ims-0.0.1-jar-with-dependencies.jar

```

#### Editing and Running in Eclipse

Open Tasdiq-Dewan-IMS-Project in Eclipse as a Maven project. Ensure the Java Build Path is set to JavaSE-17.

To run the program, run src/main/java/com/qa/ims/Runner.java as Java application. 

## Using the IMS app

### Selecting an Entity

Upon starting the application, you will be given the option to choose an entity to manage; Customer, Item, or Order ; or to stop the program.

![image](https://user-images.githubusercontent.com/37335919/178000201-772cff4a-2a5e-446d-982d-49229aecbe8c.png)

### Managing Customers

If customer is selected, you will be present with the options to read, create, update, or delete customers. Return will return you to the entity selection.

![image](https://user-images.githubusercontent.com/37335919/178000646-6e4c9c69-eec1-4520-9f0c-20fd38985087.png)

Read will read and print all customers, including ID, first name, and surname. Create will ask for a first name and surname of the new customer to be created. Update will ask for the ID of the customer you would like to update, and then the new first name and surname. Delete will ask for the ID of the customer you would like to delete.

### Managing Items

If item is selected, you will be present with the options to read, create, update, or delete items. Return will return you to the entity selection.

![image](https://user-images.githubusercontent.com/37335919/178006272-f31d3361-f90c-4ea1-9713-28e47cca8c5e.png)

Read will read and print all items, including ID, item name, and the value of the item. Create will ask for the name and value of the new item to be created. Update will ask for the ID of the item you would like to update and the new item name and value. Delete will ask for the ID of the item you would like to delete.

### Managing Orders

If order is selected, you will be present with the options to read, create, update, or delete orders; add or delete items from an order; or calculate the cost of an order. Return will return you to the entity selection.

![image](https://user-images.githubusercontent.com/37335919/178006865-1638e17c-ef6f-4187-a515-a55ef1e6f8ab.png)

Read will read and print all orders, including order ID, customer ID, as well as all fields for each item in the order. Create will ask for the ID of the customer this order is for and create a new order. Update will ask for the ID of the order you want to update and the new customer ID you are changing to. Delete will ask for the ID of the order you wish to delete.

Add_item will ask for the order ID and then the ID of the item you wish to add to the order. Delete_item will ask for the order ID and the ID of the item you wish to delete from the order.

Cost will ask for the ID of an order and will print the total cost of that order.

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Domain Tests:

There are 3 Unit tests for the com.qa.ims.persistance.domain package under src/test/java: CustomerTest.java, ItemTest.java, OrderTest.java.

These use EqualsVerifier to test the Customer, Item, and Order domain classes.

```
package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
}
```

To run these test, right click com.qa.ims.persistance.domain package under src/test/java and click Run As JUnit test

![image](https://user-images.githubusercontent.com/37335919/177976921-13cca401-f93c-4dde-bd8b-57ff3c4879e5.png)

The disparity between the Customer coverage and Item and Order coverage may be due to the auto generated equals and hashCode methods I used from eclipse for those classes. However they are still above 80% coverage.

DAO Tests:

There are 3 Unit tests for the com.qa.ims.persistance.dao package under src/test/java: CustomerDAOTest.java, ItemDAOTest.java, OrderDAOTest.java.

These perform JUnit tests of each CRUD method in the CustomerDAO, ItemDAO, and OrderDAO classes, accessing a h2 instance of the ims database schema stored in memory.

```
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
	
	@Test
	public void testDeleteItem() {
		final Long orderId = 1L, itemId = 3L;
		int expected = 1;
		Mockito.when(utils.getLong()).thenReturn(orderId, itemId);
		Mockito.when(dao.deleteItemFromOrder(orderId, itemId)).thenReturn(1);
		
		assertEquals(expected, controller.deleteItem());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).deleteItemFromOrder(orderId, itemId);
	}
	
	@Test
	public void testCost() {
		final double cost = 50.00d;
		final Long id = 1L;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(dao.costOfOrder(id)).thenReturn(cost);
		
		assertEquals(cost, controller.cost(), 0.001);
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).costOfOrder(id);
	}
}



```
To run these test, right click com.qa.ims.persistance.dao package under src/test/java and click Run As JUnit test

![image](https://user-images.githubusercontent.com/37335919/177977003-cb01f4e0-7f04-4ce6-8391-a78b63fcd210.png)

The reason these DAO tests have 75% coverage is because the catch blocks are taken into account but aren't actually reached by any test. However all try blocks pass their tests.

### Integration Tests 

There are 3 integration tests in the com.qa.ims.controllers package within src/test/java, they are CustomerControllerTest.java, ItemControllerTest.java, and OrderControllerTest.java

They test CustomerController.java, ItemController.java, and OrderController.java respectively.

Mockito is used to mock the results of the Utils instance and the respective DAO instances that each Controller depends on.

```
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

import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private CustomerDAO dao;

	@InjectMocks
	private CustomerController controller;

	@Test
	public void testCreate() {
		final String F_NAME = "barry", L_NAME = "scott";
		final Customer created = new Customer(F_NAME, L_NAME);

		Mockito.when(utils.getString()).thenReturn(F_NAME, L_NAME);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));

		Mockito.when(dao.readAll()).thenReturn(customers);

		assertEquals(customers, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Customer updated = new Customer(1L, "chris", "perrins");

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}

To run these integration tests right click com.qa.ims.controllers package within src/test/java and Run As JUnit Test.

![image](https://user-images.githubusercontent.com/37335919/177977172-6bf5ba33-a67f-467d-8690-24c0b2f79c38.png)


## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **JHarry** - *Template this project is forked from* - [jharry](https://github.com/JHarry444)
* **Tasdiq Dewan** - QA Academy trainee - [Tasdiq Dewan](https://github.com/Tasdiq-Dewan)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
