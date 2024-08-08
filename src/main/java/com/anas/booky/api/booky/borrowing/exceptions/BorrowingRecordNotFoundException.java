package com.anas.booky.api.booky.borrowing.exceptions;

public class BorrowingRecordNotFoundException extends Exception {
    public BorrowingRecordNotFoundException(final Long bookId, final Long patronId) {
        super("There is no borrowing record for book with id `" + bookId + "` and patron with id `" + patronId + "`");
    }
}
