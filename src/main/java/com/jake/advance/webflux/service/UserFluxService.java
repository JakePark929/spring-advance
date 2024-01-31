package com.jake.advance.webflux.service;

import com.jake.advance.webflux.domain.User;
import com.jake.advance.webflux.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class UserFluxService {
    //    private final UserFluxRepository userFluxRepository;
    private final UserR2dbcRepository userR2dbcRepository;

    private final ReactiveRedisTemplate<String, User> reactiveRedisTemplate;

    public Mono<User> create(String name, String email) {
        return userR2dbcRepository.save(
                User.builder()
                        .name(name)
                        .email(email)
                        .build()
        );
    }

    public Flux<User> findAll() {
        return userR2dbcRepository.findAll();
    }

    private String getUserCacheKey(Long id) {
        return "users:%d".formatted(id);
    }

    public Mono<User> findById(Long id) {
        // 1. redis 조회
        // 2. 값이 존재하면 응답을 하고
        // 3. 없으면 DB에 질의 하고 그 결과를 redis 에 저장하는 흐름
        return reactiveRedisTemplate.opsForValue()
                .get(getUserCacheKey(id))
                .switchIfEmpty(
                        userR2dbcRepository.findById(id)
                                .flatMap(u -> reactiveRedisTemplate.opsForValue()
                                        .set(getUserCacheKey(id), u, Duration.ofSeconds(30))
                                        .then(Mono.just(u))
                                )

                );
    }

    public Mono<User> update(Long id, String name, String email) {
        // 1. 해당 사용자를 찾는다
        // 2. 해당 데이터를 변경하고 저장한다.
        return userR2dbcRepository.findById(id)
                // empty -> // switchIfEmpty, defaultIfEmpty
                .flatMap(u -> {
                    u.setName(name);
                    u.setEmail(email);

                    return userR2dbcRepository.save(u);
                })
                .flatMap(u -> reactiveRedisTemplate.unlink(getUserCacheKey(id)).then(Mono.just(u)));
    }

    public Mono<Void> deleteById(Long id) {
        return userR2dbcRepository.deleteById(id)
                .then(reactiveRedisTemplate.unlink(getUserCacheKey(id)))
                .then(Mono.empty());
    }

    public Mono<Void> deleteByName(String name) {
        return userR2dbcRepository.deleteByName(name);
    }
}
