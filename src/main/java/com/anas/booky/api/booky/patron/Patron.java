package com.anas.booky.api.booky.patron;


import com.anas.booky.api.booky.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patron_gen")
    @SequenceGenerator(name = "patron_gen", sequenceName = "patron_seq")
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "patron", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Book> books = new LinkedHashSet<>();
}
