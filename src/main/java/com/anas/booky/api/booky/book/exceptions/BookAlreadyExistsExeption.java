package com.anas.booky.api.booky.book.exceptions;

public class BookAlreadyExistsExeption extends Exception {
    public BookAlreadyExistsExeption(final Long isbn) {
        super("Book with ISBN `" + isbn + "` already exists");
    }
}
