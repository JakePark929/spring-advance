package com.jake.advance.webflux.controller;

import com.jake.advance.webflux.dto.PostFluxRequest;
import com.jake.advance.webflux.dto.PostFluxResponse;
import com.jake.advance.webflux.service.PostFluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/flux2/posts")
@RestController
public class PostFluxController {
    private final PostFluxService postFluxService;

    @PostMapping("")
    public Mono<PostFluxResponse> createPost(@RequestBody PostFluxRequest request) {
        return postFluxService.create(request.getUserId(), request.getTitle(), request.getContent())
                .map(PostFluxResponse::of);
    }

    @GetMapping("")
    public Flux<PostFluxResponse> findAllPost() {
        return postFluxService.findAll().map(PostFluxResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PostFluxResponse>> findPost(@PathVariable Long id) {
        return postFluxService.findById(id)
                .map(p -> ResponseEntity.ok().body(PostFluxResponse.of(p)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deletePost(@PathVariable Long id) {
        return postFluxService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }
}
