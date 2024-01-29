package com.jake.advance.webflux.service;

import com.jake.advance.webflux.domain.User;
import com.jake.advance.webflux.repository.UserFluxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserFluxService {
    private final UserFluxRepository userFluxRepository;

    public Mono<User> create(String name, String email) {
        return userFluxRepository.save(
                User.builder()
                        .name(name)
                        .email(email)
                        .build()
        );
    }

    public Mono<User> update(Long id, String name, String email) {
        // 1. 해당 사용자를 찾는다
        // 2. 해당 데이터를 변경하고 저장한다.
        return userFluxRepository.findById(id)
                // empty -> // switchIfEmpty, defaultIfEmpty
                .flatMap(u -> {
                    u.setName(name);
                    u.setEmail(email);

                    return userFluxRepository.save(u);
                });
    }

    public Flux<User> findAll() {
        return userFluxRepository.findAll();
    }

    public Mono<User> findById(Long id) {
        return userFluxRepository.findById(id);
    }

    public Mono<Integer> deleteById(Long id) {
        return userFluxRepository.deleteById(id);
    }
}
