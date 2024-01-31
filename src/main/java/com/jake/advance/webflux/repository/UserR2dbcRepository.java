package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserR2dbcRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByName(String name);
    Flux<User> findByNameOrderByIdDesc(String name);

    @Modifying
    @Query("DELETE FROM user WHERE name = :name")
    Mono<Void> deleteByName(String name);
}
