package com.jake.advance.webflux.service;

import com.jake.advance.webflux.domain.User;
import com.jake.advance.webflux.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserFluxService {
//    private final UserFluxRepository userFluxRepository;
    private final UserR2dbcRepository userR2dbcRepository;

    public Mono<User> create(String name, String email) {
        return userR2dbcRepository.save(
                User.builder()
                        .name(name)
                        .email(email)
                        .build()
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
                });
    }

    public Flux<User> findAll() {
        return userR2dbcRepository.findAll();
    }

    public Mono<User> findById(Long id) {
        return userR2dbcRepository.findById(id);
    }

    public Mono<Void> deleteById(Long id) {
        return userR2dbcRepository.deleteById(id);
    }
}
