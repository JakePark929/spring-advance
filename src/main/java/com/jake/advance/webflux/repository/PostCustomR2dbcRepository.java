package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.Post;
import reactor.core.publisher.Flux;

public interface PostCustomR2dbcRepository {
    Flux<Post> findAllByUserId(Long userId);
}
