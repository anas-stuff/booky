package com.anas.booky.api.booky.borrowing;

import com.anas.booky.api.booky.book.Book;
import com.anas.booky.api.booky.patron.Patron;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;
    @Column(nullable = false)
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}