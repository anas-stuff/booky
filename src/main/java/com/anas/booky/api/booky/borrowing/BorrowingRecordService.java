package com.anas.booky.api.booky.borrowing;

import com.anas.booky.api.booky.book.Book;
import com.anas.booky.api.booky.book.BookRepository;
import com.anas.booky.api.booky.patron.Patron;
import com.anas.booky.api.booky.patron.PatronRepository;
import com.anas.booky.api.booky.borrowing.exceptions.BorrowingRecordNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository repository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public List<BorrowingRecord> getBorrowingRecords() {
        return repository.findAll();
    }

    public Optional<BorrowingRecord> getBorrowingRecord(final Long id) {
        return repository.findById(id);
    }

    @Transactional
    public BorrowingRecord borrowBook(final Long bookId, final Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));
        //  Check if there is an length copies of the book
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setQuantity(book.getQuantity() - 1); // Decrease the quantity of the book

        BorrowingRecord record = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .borrowingDate(LocalDate.now())
                .build();
        return repository.save(record);
    }

    @Transactional
    public BorrowingRecord returnBook(final Long bookId, final Long patronId) throws BorrowingRecordNotFoundException {
        final var record = repository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new BorrowingRecordNotFoundException(bookId, patronId));
        record.setReturnDate(LocalDate.now());
        return record;
    }
}