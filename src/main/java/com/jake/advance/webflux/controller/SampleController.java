package com.jake.advance.webflux.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController 
public class SampleController {
    @GetMapping("/sample/hello")
    public Mono<String> getHello() {
        // reactor
        // publisher <---> subscriber - 체인이 끊기지 않게 잘 전달
        return Mono.just("hello rest controller with webflux");
    }
}
