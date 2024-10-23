# Products Management API

This Spring Boot application provides a REST API for managing products.
It includes functionalities for creating, updating, deleting, and retrieving product information. The application interacts with a MySQL database and uses JPA for data persistence.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Database Configuration](#database-configuration)
- [Unit Testing](#unit-testing)

## Technologies Used
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- Hibernate (JPA implementation)
- MySQL
- Maven
- Lombok
- Mockito

## Project Structure
```
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── nobsv2/
│   │   │               ├── Command.java         # Command interface for create/update services
│   │   │               ├── Query.java           # Query interface for get/search services
│   │   │               ├── Nobsv2Application.java # Main application entry point
│   │   │               ├── exceptions/          # Contains custom exception classes
│   │   │               │   ├── ErrorMessages.java      # Enum for custom error messages
│   │   │               │   ├── GlobalExceptionHandler.java # Centralized exception handling
│   │   │               │   ├── ProductNotFoundException.java # Custom exception for product not found
│   │   │               │   └── ProductNotValidException.java # Custom exception for validation errors
│   │   │               ├── product/
│   │   │               │   ├── ProductController.java  # REST Controller for product endpoints
│   │   │               │   ├── ProductRepository.java  # Repository interface for product persistence
│   │   │               │   ├── model/                  # Contains Product, ProductDTO, UpdateProductCommand, and ErrorResponse models
│   │   │               │   └── services/               # Business logic services for product management
│   │   │               │       ├── CreateProductService.java
│   │   │               │       ├── DeleteProductService.java
│   │   │               │       ├── GetProductService.java
│   │   │               │       ├── GetProductsService.java
│   │   │               │       ├── SearchProductService.java
│   │   │               │       └── UpdateProductService.java
│   │   │               └── validators/           # Product validation logic
│   │   │                   └── ProductValidator.java
│   │   └── resources/
│   │       └── application.properties           # Application configuration (e.g., database connection)
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── nobsv2/
│                       ├── CreateProductServiceTest.java
│                       ├── DeleteProductServiceTest.java
│                       ├── GetProductServiceTest.java
│                       ├── GetProductsServiceTest.java
│                       ├── SearchProductServiceTest.java
│                       └── UpdateProductServiceTest.java
└── target/
```

## Endpoints
Here are the main REST API endpoints:

| Method | URL                | Description                 |
|--------|--------------------|-----------------------------|
| POST   | `/product`          | Create a new product        |
| GET    | `/products`         | Retrieve all products       |
| GET    | `/product/{id}`     | Retrieve a product by ID    |
| PUT    | `/product/{id}`     | Update an existing product  |
| DELETE | `/product/{id}`     | Delete a product by ID      |
| GET    | `/product/search`   | Search for products by name |

### Example of Request/Response
- **POST /product**
  - Request Body:
    ```json
    {
      "name": "Laptop",
      "description": "A powerful laptop",
      "price": 1500.00
    }
    ```
  - Response Body:
    ```json
    {
      "id": 1,
      "name": "Laptop",
      "description": "A powerful laptop"
    }
    ```

- **GET /products**
  - Response Body:
    ```json
    [
      {
        "id": 1,
        "name": "Laptop",
        "description": "A powerful laptop"
      },
      {
        "id": 2,
        "name": "Phone",
        "description": "A smartphone with a great camera"
      }
    ]
    ```

## Database Configuration
Make sure to set up your MySQL database and update the `application.properties` file with the correct credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nobsv2
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
## Unit Testing
Unit tests are implemented for the service layer using Mockito. Tests include creating, updating, deleting, and retrieving products.

Here are the test classes:

- CreateProductServiceTest.java
- DeleteProductServiceTest.java
- GetProductServiceTest.java
- GetProductsServiceTest.java
- SearchProductServiceTest.java
- UpdateProductServiceTest.java

To run the tests, use the following Maven command:
```
 bash $ mvn test
```
Example Test Assertion:
```
 assertEquals(ResponseEntity.status(HttpStatus.OK).body(expectedDTOs), response);
```
This project is designed for efficient management of products, offering a comprehensive set of REST API operations along with robust validation and exception handling mechanisms.