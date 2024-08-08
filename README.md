## Booky api:  Library Management System

## Project Description

The Library Management System is a RESTful API built using Spring Boot. It allows librarians to manage books, patrons, and borrowing records efficiently. The system provides endpoints for creating, retrieving, updating, and deleting book and patron records, as well as managing the borrowing and returning of books.

## Features

- **Book Management**: CRUD operations for managing books in the library.
- **Patron Management**: CRUD operations for managing patrons.
- **Borrowing Records**: Track and manage the borrowing and returning of books by patrons.
- **Validation and Error Handling**: Ensure proper input validation and handle exceptions gracefully.
- **Transaction Management**: Ensure data integrity during critical operations.
 - Basic authentication or JWT-based authorization.
- Aspect-Oriented Programming (AOP) for logging.
- Caching to improve performance.

## Technologies Used

- **Spring Boot**: Framework for building the API.
- **Database**: PostgreSQL.
- **JPA/Hibernate**: For ORM and database interactions.
- **JUnit/Mockito**: For unit testing.
- **Spring Security**: For authentication and authorization.
- **Swagger**: For API documentation.
- **Lombok**: For reducing boilerplate code.
- **WebFlux**: For reactive programming.
- **Docker**: For containerization (optional).

## Setup and Installation

### Prerequisites

-  Docker :)

### Clone the Repository

```sh
git clone https://github.com/anas-stuff/booky-api.git
cd booky-api
```

###  Start the PostgreSQL database

```sh
docker build --network=host -t booky-api-db -f db.dockerfile .
docker run --name booky-api-db -d -p 5432:5432 booky-api-db
```

### Build the spring boot application container 

```sh
docker build --network=host -t booky-api-boot-app -f Dockerfile .
```

### Start the server

```sh
docker run --network=host --name booky-api-boot-app -d -p 8080:8080 booky-api-boot-app
```

### Access the API

The API will be accessible at `http://localhost:8080/`.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui/index.html`.

### API expected flow

1. Login to the system using the `/login` endpoint.
    > At the start of the  application, there is only one super admin user with the following credentials:
   > - Username: `root@root.local`
   > - Password: `root`
2. Use the token received from the login response to access other endpoints.
3. Create another low privileged admin using the `/api/admin/add` endpoint.
4. Use the new admin credentials to login and access the system.
5. Create books and patrons using the `/api/books` and `/api/patrons` endpoints.
6. Borrow and return books using the `/api/borrow` and `/api/return` endpoints.
7. View borrowing records using the `/api/borrow/records` endpoint.