package com.anas.booky.api.booky.book;

import com.anas.booky.api.booky.book.exceptions.BookAlreadyExistsExeption;
import com.anas.booky.api.booky.book.exceptions.BookNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BookService {
    private final BookRepository repository;

    /**
     * Get all books
     * @return List of all books
     */
    public List<Book> getBooks() {
        return repository.findAll();
    }

    /**
     * Get a book by id
     * @param id The id of the book
     * @return The book with the given id
     */
    public Optional<Book> getBook(final Long id) {
        return repository.findById(id);
    }

    /**
     * Save a new book
     * @param book The book to save
     * @return The saved book
     * @throws BookAlreadyExistsExeption If a book with the same ISBN already exists
     */
    public Book saveBook(final Book book) throws BookAlreadyExistsExeption {
        // Check if there is a book with the same ISBN already in the database
        if (this.getBooks().stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()))) {
            throw new BookAlreadyExistsExeption(book.getIsbn());
        }
        return repository.save(book);
    }

    /**
     * Update a book
     * @param id The id of the book to update
     * @param book The new book details
     * @return The updated book
     * @throws BookNotFoundException If the book with the given id does not exist
     * @throws BookAlreadyExistsExeption If a book with the same ISBN already exists
     */
    @Transactional
    public Book updateBook(final Long id, final Book book) throws BookNotFoundException, BookAlreadyExistsExeption {
        // Check if the book exists
        var existingBook = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        // Check if there is another book with the same ISBN
        var existingBookWithSameIsbn = repository.findByIsbn(book.getIsbn());
        if (existingBookWithSameIsbn.isPresent() && !existingBookWithSameIsbn.get().getId().equals(id)) {
            throw new BookAlreadyExistsExeption(book.getIsbn());
        }

        // Update the book details
        if (book.getTitle() != null && !book.getTitle().isEmpty()) {
            existingBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (book.getDescription() != null && !book.getDescription().isEmpty()) {
            existingBook.setDescription(book.getDescription());
        }
        if (book.getCoverPhotoURL() != null && !book.getCoverPhotoURL().isEmpty()) {
            existingBook.setCoverPhotoURL(book.getCoverPhotoURL());
        }
        if (book.getIsbn() != null) {
            existingBook.setIsbn(book.getIsbn());
        }
        if (book.getPrice() != null) {
            existingBook.setPrice(book.getPrice());
        }
        if (book.getPublicationDate() != null) {
            existingBook.setPublicationDate(book.getPublicationDate());
        }
        if (book.getQuantity() != null && book.getQuantity() >= 0) {
            existingBook.setQuantity(book.getQuantity());
        }

        // Save the updated book
        return existingBook;
    }

    /**
     * Delete a book
     * @param id The id of the book to delete
     * @throws BookNotFoundException If the book with the given id does not exist
     */
    public void deleteBook(final Long id) throws BookNotFoundException {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
