package com.anas.booky.api.booky.book;

import com.anas.booky.api.booky.book.exceptions.BookAlreadyExistsExeption;
import com.anas.booky.api.booky.book.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService service;

    @InjectMocks
    private BookController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBooks_returnsListOfBooks() {
        final var books = List.of(new Book(), new Book());
        when(service.getBooks()).thenReturn(books);

        final var result = controller.getBooks();

        assertEquals(books, result);
    }

    @Test
    void getBook_returnsBook_whenBookExists() {
        final var id = 1L;
        final var book = new Book();
        when(service.getBook(id)).thenReturn(Optional.of(book));

        final var response = controller.getBook(id);

        assertEquals(ResponseEntity.ok(book), response);
    }

    @Test
    void getBook_returnsNotFound_whenBookDoesNotExist() {
        final var id = 1L;
        when(service.getBook(id)).thenReturn(Optional.empty());

        final var response = controller.getBook(id);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void saveBook_savesAndReturnsBook() throws BookAlreadyExistsExeption {
        final var book = new Book();
        when(service.saveBook(book)).thenReturn(book);

        final var result = controller.saveBook(book);

        assertEquals(book, result);
    }

    @Test
    void updateBook_updatesAndReturnsBook() throws BookAlreadyExistsExeption, BookNotFoundException {
        final var id = 1L;
        final var book = new Book();
        when(service.updateBook(id, book)).thenReturn(book);

        final var result = controller.updateBook(id, book);

        assertEquals(book, result);
    }

    @Test
    void deleteBook_deletesBook() throws BookNotFoundException {
        final var id = 1L;
        doNothing().when(service).deleteBook(id);

        controller.deleteBook(id);

        verify(service, times(1)).deleteBook(id);
    }
}