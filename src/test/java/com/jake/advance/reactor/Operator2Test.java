package com.jake.advance.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class Operator2Test {
    private Operator2 operator = new Operator2();
    @Test
    void fluxConcatMap() {
        StepVerifier.create(operator.fluxConcatMap())
                .expectNextCount(100)
                .verifyComplete();
    }

    @Test
    void monoFlatMapMany() {
        StepVerifier.create(operator.monoFlatMapMany())
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void defaultItIfEmpty() {
        StepVerifier.create(operator.defaultIfEmpty())
                .expectNext(30)
                .verifyComplete();
    }

    @Test
    void switchIfEmpty() {
        StepVerifier.create(operator.switchIfEmpty())
                .expectNext(60)
                .verifyComplete();
    }

    @Test
    void switchIfEmptyWithThrow() {
        StepVerifier.create(operator.switchIfEmptyWithThrow())
                .expectError()
                .verify();
    }


    @Test
    void fluxMerge() {
        StepVerifier.create(operator.fluxMerge())
                .expectNext("1", "2", "3", "4")
                .verifyComplete();
    }

    @Test
    void monoMerge() {
        StepVerifier.create(operator.monoMerge())
                .expectNext("1", "2", "3")
                .verifyComplete();
    }

    @Test
    void fluxZip() {
        StepVerifier.create(operator.fluxZip())
                .expectNext("ad", "be", "cf")
                .verifyComplete();
    }

    @Test
    void monoZip() {
        StepVerifier.create(operator.monoZip())
                .expectNext(6)
                .verifyComplete();
    }
}