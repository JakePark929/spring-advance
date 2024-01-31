package com.jake.advance.webflux.client;

import com.jake.advance.webflux.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostClient {
    private final WebClient webClient;
    private final String url = "http://127.0.0.1:8090";

    // WebClient -> mvc("/posts/id")
    public Mono<PostResponse> getPost(Long id) {
        final String uriString = UriComponentsBuilder.fromHttpUrl(url)
                .path("/api/posts/%d".formatted(id))
                .buildAndExpand()
                .toUriString();

        return webClient.get()
                .uri(uriString)
                .retrieve()
                .bodyToMono(PostResponse.class);
    }
}
