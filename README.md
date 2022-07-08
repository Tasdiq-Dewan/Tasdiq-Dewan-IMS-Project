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

#### Editing and Running in Eclipse

Open Tasdiq-Dewan-IMS-Project in Eclipse as a Maven project. Ensure the Java Build Path is set to JavaSE-17.

To run the programm, run asdiq-Dewan-IMS-Project/src/main/java/com/qa/ims/Runner.java as Java application. 

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


public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
```

To run these test, right click com.qa.ims.persistance.domain package under src/test/java and click Coverage As JUnit test

DAO Tests:

There are 3 Unit tests for the com.qa.ims.persistance.dao package under src/test/java: CustomerDAOTest.java, ItemDAOTest.java, OrderDAOTest.java.

These perform JUnit tests of each CRUD method in the DAO classes, accessing a h2 instance of the ims database schema stored in memory.

```
package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}

```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

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
