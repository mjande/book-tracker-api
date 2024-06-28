package com.github.api.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.api.models.Book;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BookEndpointTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAllBooksReturnsString() {
        webTestClient.get()
            .uri("/books")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("All books");
    }

    @Test
    public void getBookByIdReturnsString() {
        webTestClient.get()
            .uri("/books/2")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("Book with ID 2");
    }

    @Test
    public void createBookReturnsString() {
        Book newBook = new Book();
        newBook.setId(1);
        newBook.setTitle("TestBook");
        newBook.setAuthor("TestAuthor");
        newBook.setPublicationYear(2024);
        newBook.setGenre("TestGenre");

        Mono<Book> bookMono = Mono.just(newBook);
        
        webTestClient.post()
            .uri("/books")
            .body(bookMono, Book.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(String.class)
            .isEqualTo("Book was created: " + newBook.toString());
    }

    @Test
    public void updateBookReturnsString() {
        Book updatedBook = new Book();
        updatedBook.setId(1);
        updatedBook.setTitle("TestBook");
        updatedBook.setAuthor("TestAuthor");
        updatedBook.setPublicationYear(2024);
        updatedBook.setGenre("TestGenre");

        Mono<Book> bookMono = Mono.just(updatedBook);

        webTestClient.put()
            .uri("/books/1")
            .body(bookMono, Book.class)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("Book with id 1 was updated: " + updatedBook.toString());
    }

    @Test
    public void deleteBookReturnsString() {
        webTestClient.delete()
            .uri("/books/2")
            .exchange()
            .expectStatus()
            .isNoContent()
            .expectBody()
            .isEmpty();
    }
}
