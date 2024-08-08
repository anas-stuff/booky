package com.anas.booky.api.booky.book.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(final Long id) {
        super("Book with id `" + id + "` not found");
    }
}
