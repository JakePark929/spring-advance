package com.jake.advance.webflux.service;

import com.jake.advance.webflux.domain.Post;
import com.jake.advance.webflux.repository.PostR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostFluxService {
    private final PostR2dbcRepository postR2dbcRepository;

    // create
    public Mono<Post> create(Long userId, String title, String content) {
        return postR2dbcRepository.save(
                Post.builder()
                        .userId(userId)
                        .title(title)
                        .content(content)
                        .build()
        );
    }

    // read
    public Flux<Post> findAll() {
        return postR2dbcRepository.findAll();
    }

    public Mono<Post> findById(Long id) {
        return postR2dbcRepository.findById(id);
    }

    // delete
    public Mono<Void> deleteById(Long id) {
        return postR2dbcRepository.deleteById(id);
    }
}
