package com.anas.booky.api.booky.book;

import com.anas.booky.api.booky.book.exceptions.BookAlreadyExistsExeption;
import com.anas.booky.api.booky.book.exceptions.BookNotFoundException;
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
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return service.getBook(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) throws BookAlreadyExistsExeption {
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