package com.jake.advance.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("post")
@Data
public class Post {
    @Id
    private Long id;
    @Column("userId")
    private Long userId;
    private String title;
    private String content;

    @Transient
    private User user;

    @Column("createdAt")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column("updatedAt")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
