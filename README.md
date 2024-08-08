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
- **Spring Security**: For authentication and authorization (optional).
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

The API documentation is available at `http://localhost:8080/swagger-ui.html`.
