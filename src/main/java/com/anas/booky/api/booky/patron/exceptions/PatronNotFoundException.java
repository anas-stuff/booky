package com.anas.booky.api.booky.patron.exceptions;

public class PatronNotFoundException extends Exception {
    public PatronNotFoundException(final Long id) {
        super("Patron with id `" + id + "` not found");
    }
}
