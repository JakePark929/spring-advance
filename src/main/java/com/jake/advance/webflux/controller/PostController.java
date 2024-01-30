package com.jake.advance.webflux.controller;

import com.jake.advance.webflux.dto.PostResponse;
import com.jake.advance.webflux.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/flux/posts")
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public Mono<PostResponse> getPostContent(@PathVariable Long id) {
        return postService.getPostContent(id);
    }

    @GetMapping("/search")
    public Flux<PostResponse> getMultiplePostContent(@RequestParam(name = "ids") List<Long> idList) {
        return postService.getMultiplePostContent(idList);
    }

    @GetMapping("/search-parallel")
    public Flux<PostResponse> getParallelMultiplePostContent(@RequestParam(name = "ids") List<Long> idList) {
        return postService.getParallelMultiplePostContent(idList);
    }
}
