package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.User;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class UserFluxRepositoryTest {
    private final UserFluxRepository userFluxRepository = new UserRepositoryImpl();

    @Test
    void save() {
        final User user = User.builder().name("greg").email("greg@email.com").build();

        StepVerifier.create(userFluxRepository.save(user))
                .assertNext(u -> {
                    assertEquals(1L, u.getId());
                    assertEquals("greg", u.getName());
                })
                .verifyComplete();
    }

    @Test
    void findAll() {
        userFluxRepository.save(User.builder().name("greg").email("greg@email.com").build());
        userFluxRepository.save(User.builder().name("greg2").email("greg2@email.com").build());
        userFluxRepository.save(User.builder().name("greg3").email("greg3@email.com").build());

        StepVerifier.create(userFluxRepository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findById() {
        userFluxRepository.save(User.builder().name("greg").email("greg@email.com").build());
        userFluxRepository.save(User.builder().name("greg2").email("greg2@email.com").build());

        StepVerifier.create(userFluxRepository.findById(2L))
                .assertNext(u -> {
                    assertEquals(2L, u.getId());
                    assertEquals("greg2", u.getName());
                })
                .verifyComplete();
    }

    @Test
    void deleteById() {
        userFluxRepository.save(User.builder().name("greg").email("greg@email.com").build());
        userFluxRepository.save(User.builder().name("greg2").email("greg2@email.com").build());

        StepVerifier.create(userFluxRepository.deleteById(2L))
                .expectNextCount(1)
                .verifyComplete();
    }
}