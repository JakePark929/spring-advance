package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserR2dbcRepository extends ReactiveCrudRepository<User, Long> {
}
