package com.anas.booky.api.booky.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner bookCommandLineRunner(final BookRepository repository) {
        return args -> repository.saveAll(List.of(
                Book.builder()
                        .title("The Great Gatsby")
                        .author("F. Scott Fitzgerald")
                        .description("The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City," +
                                " the novel depicts first-person narrator Nick Carraway's interactions with mysterious" +
                                " millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.")
                        .coverPhotoURL("https://images-na.ssl-images-amazon.com/images/I/51ZQj5v3PjL._SX331_BO1,204,203,200_.jpg")
                        .isbn(9780743273565L)
                        .price(7.99)
                        .publicationDate(LocalDate.of(1925, 4, 10))
                        .quantity(10)
                        .build(),
                Book.builder()
                        .title("To Kill a Mockingbird")
                        .author("Harper Lee")
                        .description("To Kill a Mockingbird is a novel by Harper Lee published in 1960. Instantly successful, widely read in high schools and middle schools in the United States, it has become a classic of modern American literature, winning the Pulitzer Prize.")
                        .coverPhotoURL("https://images-na.ssl-images-amazon.com/images/I/51ZQj5v3PjL._SX331_BO1,204,203,200_.jpg")
                        .isbn(9780060935467L)
                        .price(6.99)
                        .publicationDate(LocalDate.of(1960, 7, 11))
                        .quantity(3)
                        .build(),
                Book.builder()
                        .title("1984")
                        .author("George Orwell")
                        .description("Nineteen Eighty-Four: A Novel, often referred to as 1984, is a dystopian social science fiction novel by the English novelist George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime.")
                        .coverPhotoURL("https://images-na.ssl-images-amazon.com/images/I/51ZQj5v3PjL._SX331_BO1,204,203,200_.jpg")
                        .isbn(9780451524935L)
                        .price(8.99)
                        .publicationDate(LocalDate.of(1949, 6, 8))
                        .quantity(5)
                        .build(),
                Book.builder()
                        .title("Animal Farm")
                        .author("George Orwell")
                        .description("Animal Farm is an allegorical novella by George Orwell, first published in England on 17 August 1945. The book tells the story of a group of farm animals who rebel against their human farmer, hoping to create a society where the animals can be equal, free, and happy.")
                        .coverPhotoURL("https://images-na.ssl-images-amazon.com/images/I/51ZQj5v3PjL._SX331_BO1,204,203,200_.jpg")
                        .isbn(9780451526342L)
                        .price(5.99)
                        .publicationDate(LocalDate.of(1945, 8, 17))
                        .quantity(7)
                        .build()
        ));
    }
}