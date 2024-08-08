package com.anas.booky.api.booky.patron.exceptions;

public class PatronAlreadyExistsExeption extends IllegalAccessException {
    public PatronAlreadyExistsExeption(final String email) {
        super("Patron with email `" + email + "` already exists");
    }
}
