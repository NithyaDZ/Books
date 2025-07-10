# Books
CRUD application for managing a collection of  Books

# Technologies

- Java 17+
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database
- Maven
  
# Features

- Add a new book (`POST /api/books`)
- View all books (`GET /api/books`)
- View a book by ID (`GET /api/books/{id}`)
- Update book details (`PUT /api/books/{id}`)
- Delete a book (`DELETE /api/books/{id}`)
- H2 in-memory database
- Bean validation (e.g., `@NotBlank`, `@Valid`)
- Layered architecture (Controller, Service, Repository, Entity)

# How to Run

1. Clone the repository
   - git clone https://github.com/your-username/bookcrud.git
   - cd bookcrud

2. Run the application
   ./mvnw spring-boot:run

3. Access the endpoints
    Base URL: http://localhost:8080

4. Test API endpoints
## post
     /api/books
   - json file
           {
              "title": "The Great Gatsby",
              "author": "F. Scott Fitzgerald",
              "isbn": "978-0743273565"
            }

## get
     /api/books

## get (by id)
     /api/books/{id}

## put (update book by id)
     /api/books/{id}
   - json file
           {
              "title": "Updated Title",
              "author": "Updated Author",
              "isbn": "111-2223334445"
            }

   
## delete
     /api/books/{id}

# Assumptions
- ISBN must be unique.
- @NotBlank validation used for title and author.
