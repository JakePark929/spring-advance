package com.jake.advance.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Publisher {
    public Flux<Integer> startFlux() {
//        Flux.just(1, 2, 3, 4, 5);
//        Flux.fromIterable(List.of("a", "b", "c"));
        return Flux.range(1, 10).log();
    }

    public Flux<String> startFluxIterable() {
        return Flux.fromIterable(List.of("a", "b", "c", "d")).log();
    }

    public Mono<Integer> startMono() {
        return Mono.just(1).log();
    }

    public Mono<?> startMonoEmpty() {
        return Mono.empty().log();
    }

    public Mono<?> startMonoError() {
        return Mono.error(new Exception("hello reactor exception")).log();
    }
}
