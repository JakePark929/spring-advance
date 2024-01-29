package com.jake.advance.webflux.controller;

import com.jake.advance.webflux.domain.User;
import com.jake.advance.webflux.dto.UserCreateRequest;
import com.jake.advance.webflux.dto.UserResponse;
import com.jake.advance.webflux.service.UserFluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest(UserFluxController.class)
@AutoConfigureWebTestClient
class UserFluxControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserFluxService userFluxService;

    @Test
    void createUser() {
        // When
        when(userFluxService.create("greg", "greg@email.com")).thenReturn(
                // When & Then
                Mono.just(new User(1L, "greg", "greg@email.com", LocalDateTime.now(), LocalDateTime.now()))
        );

        // Then
        webTestClient.post().uri("/flux/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateRequest("greg", "greg@email.com"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(UserResponse.class)
                .value(res -> {
                    assertEquals("greg", res.getName());
                    assertEquals("greg@email.com", res.getEmail());
                });
    }

    @Test
    void updateUser() {
        // When
        when(userFluxService.update(1L, "greg1", "greg1@email.com")).thenReturn(
                // When & Then
                Mono.just(new User(1L, "greg1", "greg1@email.com", LocalDateTime.now(), LocalDateTime.now()))
        );

        // Then
        webTestClient.put().uri("/flux/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateRequest("greg1", "greg1@email.com"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(UserResponse.class)
                .value(res -> {
                    assertEquals("greg1", res.getName());
                    assertEquals("greg1@email.com", res.getEmail());
                });
    }

    @Test
    void findAllUsers() {
        when(userFluxService.findAll()).thenReturn(
                Flux.just(
                        new User(1L, "greg", "greg@email.com", LocalDateTime.now(), LocalDateTime.now()),
                        new User(2L, "greg", "greg@email.com", LocalDateTime.now(), LocalDateTime.now()),
                        new User(3L, "greg", "greg@email.com", LocalDateTime.now(), LocalDateTime.now())
                )
        );

        webTestClient.get().uri("/flux/users")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(UserResponse.class)
                .hasSize(3);
    }

    @Test
    void findUser() {
        // When
        when(userFluxService.findById(1L)).thenReturn(
                Mono.just(new User(1L, "greg", "greg@email.com", LocalDateTime.now(), LocalDateTime.now()))
        );

        // Then
        webTestClient.get().uri("/flux/users/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(UserResponse.class)
                .value(res -> {
                    assertEquals("greg", res.getName());
                    assertEquals("greg@email.com", res.getEmail());
                });
    }

    @Test
    void notFoundUser() {
        // When
        when(userFluxService.findById(1L)).thenReturn(Mono.empty());

        // Then
        webTestClient.get().uri("/flux/users/1")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void deleteUser() {
        when(userFluxService.deleteById(1L)).thenReturn(Mono.just(1));

        webTestClient.delete().uri("/flux/users/1")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}