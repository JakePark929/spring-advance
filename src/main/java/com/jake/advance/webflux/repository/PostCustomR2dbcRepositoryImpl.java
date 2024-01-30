package com.jake.advance.webflux.repository;

import com.jake.advance.webflux.domain.Post;
import com.jake.advance.webflux.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Repository
public class PostCustomR2dbcRepositoryImpl implements PostCustomR2dbcRepository {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<Post> findAllByUserId(Long userId) {
        var sql = """
                    SELECT p.id as pid, p.userId as userId, p.title, p.content, p.createdAt as createdAt, p.updatedAt as updatedAt,
                        u.id as uid, u.name as name, u.email as email, u.createdAt as uCreatedAt, u.updatedAt as uUpdatedAt 
                    FROM post p 
                    LEFT JOIN user u ON p.userId = u.id 
                    WHERE p.userId = :userId
                """;

        return databaseClient.sql(sql).bind("userId", userId)
                .fetch()
                .all()
                .map(row -> new Post().builder()
                        .id((Long) row.get("pid"))
                        .userId((Long) row.get("userId"))
                        .title((String) row.get("title"))
                        .content((String) row.get("content"))
                        .user(
                                User.builder()
                                        .id((Long) row.get("uid"))
                                        .name((String) row.get("name"))
                                        .email((String) row.get("email"))
                                        .createdAt(((ZonedDateTime) row.get("uCreatedAt")).toLocalDateTime())
                                        .updatedAt(((ZonedDateTime) row.get("uUpdatedAt")).toLocalDateTime())
                                        .build()
                        )
                        .createdAt(((ZonedDateTime) row.get("createdAt")).toLocalDateTime())
                        .updatedAt(((ZonedDateTime) row.get("updatedAt")).toLocalDateTime())
                        .build());
    }
}
