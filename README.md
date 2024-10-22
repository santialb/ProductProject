# Products Management API

This Spring Boot application provides a REST API for managing products.
It includes functionalities for creating, updating, deleting, and retrieving product information. The application interacts with a MySQL database and uses JPA for data persistence.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Database Configuration](#database-configuration)

## Technologies Used
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- Hibernate (JPA implementation)
- MySQL
- Maven
- Lombok

## Project Structure
```
├── HELP.md
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/nobsv2/
│   │   │       ├── product/
│   │   │       │   ├── ProductController.java        # REST Controller for product endpoints
│   │   │       │   ├── ProductRepository.java        # Repository interface for product persistence
│   │   │       │   ├── model/                        # Contains Product, ProductDTO, and ErrorResponse models
│   │   │       │   └── services/                     # Business logic services for product management
│   │   │       └── exceptions/                       # Custom exceptions and global exception handling
│   └── resources/
│       └── application.properties                     # Application configuration (e.g., database connection)
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


## Database Configuration
Make sure to set up your MySQL database and update the `application.properties` file with the correct credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nobsv2
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

