package com.github.api.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.api.models.User;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserEndpointTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAllUsersReturnsString() {
        webTestClient.get()
            .uri("/users")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("All users");
    }

    @Test
    public void getUserByIdReturnsString() {
        webTestClient.get()
            .uri("/users/1")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("User with ID 1");
    }

    @Test
    public void createUserReturnsString() {
        User newUser = new User();
        newUser.setId(1);
        newUser.setName("testName");
        newUser.setEmail("test@email.com");
        newUser.setPassword("testPassword");
        newUser.setType("testType");

        Mono<User> userMono = Mono.just(newUser);
        
        webTestClient.post()
            .uri("/users")
            .body(userMono, User.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(String.class)
            .isEqualTo("User was created: " + newUser);
    }

    @Test
    public void updateUserReturnsString() {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setName("testName");
        updatedUser.setEmail("test@email.com");
        updatedUser.setPassword("testPassword");
        updatedUser.setType("testType");

        Mono<User> UserMono = Mono.just(updatedUser);

        webTestClient.put()
            .uri("/users/1")
            .body(UserMono, User.class)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("User with id 1 was updated: " + updatedUser.toString());
    }

    @Test
    public void deleteUserReturnsNoContent() {
        webTestClient.delete()
            .uri("/users/2")
            .exchange()
            .expectStatus()
            .isNoContent()
            .expectBody()
            .isEmpty();
    }
}
