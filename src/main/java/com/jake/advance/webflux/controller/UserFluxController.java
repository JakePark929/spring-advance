package com.jake.advance.webflux.controller;

import com.jake.advance.webflux.dto.UserCreateRequest;
import com.jake.advance.webflux.dto.UserPostResponse;
import com.jake.advance.webflux.dto.UserResponse;
import com.jake.advance.webflux.dto.UserUpdateRequest;
import com.jake.advance.webflux.service.PostFluxService;
import com.jake.advance.webflux.service.UserFluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/flux/users")
@RestController
public class UserFluxController {
    private final UserFluxService UserFluxService;
    private final PostFluxService postFluxService;

    // CRUD
    @PostMapping("")
    public Mono<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        return UserFluxService.create(request.getName(), request.getEmail())
                .map(UserResponse::of);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        // 404 not found
        // user (o): 200 ok
        return UserFluxService.update(id, request.getName(), request.getEmail())
                .map(u -> ResponseEntity.ok(UserResponse.of(u)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("")
    public Flux<UserResponse> findAllUsers() {
        return UserFluxService.findAll().map(UserResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> findUser(@PathVariable Long id) {
        return UserFluxService.findById(id)
                .map(u -> ResponseEntity.ok(UserResponse.of(u)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/{id}/posts")
    public Flux<UserPostResponse> getUserPosts(@PathVariable Long id) {
        return postFluxService.findAllByUserId(id)
                .map(UserPostResponse::of);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteUser(@PathVariable Long id) {
        // 204(no content)
        return UserFluxService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @DeleteMapping("/search")
    public Mono<ResponseEntity<?>> deleteUser(@RequestParam String name) {
        // 204(no content)
        return UserFluxService.deleteByName(name).then(Mono.just(ResponseEntity.noContent().build()));
    }
}
