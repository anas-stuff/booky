// BorrowingRecordController.java
package com.anas.booky.api.booky.borrowing;

import com.anas.booky.api.booky.borrowing.exceptions.BorrowingRecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BorrowingRecordController {
    private final BorrowingRecordService service;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return ResponseEntity.ok(service.borrowBook(bookId, patronId));
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) throws BorrowingRecordNotFoundException {
        return ResponseEntity.ok(service.returnBook(bookId, patronId));
    }
}