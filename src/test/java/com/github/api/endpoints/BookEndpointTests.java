package com.github.api.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

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
            .expectBody(String.class)
            .isEqualTo("All books");
    }

    @Test
    public void getBookByIdReturnsString() {
        webTestClient.get()
            .uri("/books/2")
            .exchange()
            .expectBody(String.class)
            .isEqualTo("Book with ID 2");
    }
}
