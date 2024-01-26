package com.jake.advance.lettuce.repository;

import com.jake.advance.lettuce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
