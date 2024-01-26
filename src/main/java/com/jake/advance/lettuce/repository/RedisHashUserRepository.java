package com.jake.advance.lettuce.repository;

import com.jake.advance.lettuce.domain.RedisHashUser;
import org.springframework.data.repository.CrudRepository;

public interface RedisHashUserRepository extends CrudRepository<RedisHashUser, Long> {
}
