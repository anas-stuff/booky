package com.anas.booky.api.booky.admin.exceptions;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(final Long id) {
        super("There is no admin with id `" + id + "`");
    }

    public AdminNotFoundException(final String email) {
        super("There is no admin with email `" + email + "`");
    }
}
