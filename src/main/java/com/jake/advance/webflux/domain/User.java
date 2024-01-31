package com.jake.advance.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user")
@Data
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    @Column("createdAt")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column("updatedAt")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
