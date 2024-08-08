package com.anas.booky.api.booky.book;

import com.anas.booky.api.booky.book.exceptions.BookAlreadyExistsExeption;
import com.anas.booky.api.booky.book.exceptions.BookNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {
    private final BookService service;

    @GetMapping
    @Operation(summary = "Get all books")
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    @ApiResponse(responseCode = "200", description = "Book found",
            content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json") })
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return service.getBook(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Save a book")
    @ApiResponse(responseCode = "200", description = "Book saved",
            content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json") })
    @ApiResponse(responseCode = "409", description = "Book already exists")
    public ResponseEntity<Book> saveBook(@RequestBody final Book book) throws BookAlreadyExistsExeption {
        return ResponseEntity.ok(service.saveBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) throws BookNotFoundException, BookAlreadyExistsExeption {
        return ResponseEntity.ok(service.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws BookNotFoundException {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}