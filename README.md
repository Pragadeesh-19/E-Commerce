# E-Commerce Backend

## Overview

This is an e-commerce backend application built using Java and Spring Boot. It provides a RESTful API for managing users, carts, orders, and products. The application integrates with PostgreSQL for data persistence and uses JWT for authentication and authorization.

## Features

- **User Authentication**: Register and log in users with JWT authentication.
- **Cart Management**: Create and manage shopping carts, add or remove items.
- **Order Management**: Create, update, retrieve, and delete orders.
- **Product Management**: Add, update, retrieve, and delete products.

## Technologies

- **Java**: Programming language used for backend development.
- **Spring Boot**: Framework for building the RESTful API.
- **Spring Data JPA**: ORM framework for data access and persistence.
- **Spring Security**: Provides authentication and authorization using JWT.
- **PostgreSQL**: Database for storing data.
- **Postman**: Tool used for testing API endpoints.

## Getting Started

### Prerequisites

- Java 22
- Maven
- PostgreSQL
- Postman (for testing)

### Installation

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/Pragadeesh-19/E-Commerce.git
    cd E-Commerce
    ```

2. **Configure the Database:**

    Create a PostgreSQL database and update the `application.properties` file with your database credentials:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

3. **Build and Run the Application:**

    Using Maven:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### API Endpoints

#### Authentication

- **Register User**
  - `POST /register` - Register a new user. Requires a JSON body with user details.
- **Login User**
  - `POST /login` - Authenticate a user and get a JWT token. Requires a JSON body with user credentials.

#### Cart Management

- **Create Cart**
  - `POST /api/cart/create/{userId}` - Create a cart for a user.
- **Add Items to Cart**
  - `POST /api/cart/{cartId}/items` - Add items to a cart. Requires query parameters `productId` and `quantity`.
- **Update Cart Item**
  - `PUT /api/cart/items/{cartItemId}` - Update the quantity of a cart item. Requires query parameter `quantity`.
- **Remove Item from Cart**
  - `DELETE /api/cart/items/{cartItemId}` - Remove an item from a cart.
- **Get Cart Items**
  - `GET /api/cart/{cartId}/items` - Retrieve all items in a cart.

#### Order Management

- **Create Order**
  - `POST /api/order` - Create a new order. Requires query parameter `userId` and a JSON body with order items.
- **Get Order by ID**
  - `GET /api/order/{id}` - Retrieve an order by its ID.
- **Update Order**
  - `PUT /api/order/{id}` - Update an order by its ID. Requires a JSON body with updated order details.
- **Delete Order**
  - `DELETE /api/order/{id}` - Delete an order by its ID.

#### Product Management

- **Create Product**
  - `POST /api/v1/products/create` - Add a new product. Requires a JSON body with product details.
- **Get Product by ID**
  - `GET /api/v1/products/{id}` - Retrieve a product by its ID.
- **Update Product**
  - `PUT /api/v1/products/update/{id}` - Update a product by its ID. Requires a JSON body with updated product details.
- **Delete Product**
  - `DELETE /api/v1/products/{id}` - Delete a product by its ID.

### Testing

You can use Postman to test the API endpoints. Import the Postman collection provided in the repository for easier testing.

### Contributing

Contributions are welcome! Please open an issue or submit a pull request for any changes or enhancements.
