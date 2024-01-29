package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserFluxRepository {
    // Create, Update
    Mono<User> save(User user);
    // Read
    Flux<User> findAll();
    Mono<User> findById(Long id);
    // Delete
    Mono<Integer> deleteById(Long id);
}

