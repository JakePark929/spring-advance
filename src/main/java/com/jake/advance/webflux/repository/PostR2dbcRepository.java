package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostR2dbcRepository extends R2dbcRepository<Post, Long> {
}
