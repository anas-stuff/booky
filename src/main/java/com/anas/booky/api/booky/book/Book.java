package com.anas.booky.api.booky.book;

import com.anas.booky.api.booky.patron.Patron;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @Column(insertable=false, updatable=false, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(length = 2024)
    private String description;
    @Column(nullable = false)
    private String coverPhotoURL;
    @Column(nullable = false, unique = true)
    private Long isbn;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private LocalDate publicationDate;
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patron patron;
}
