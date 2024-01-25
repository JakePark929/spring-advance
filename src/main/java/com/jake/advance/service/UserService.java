package com.jake.advance.service;

import com.jake.advance.domain.User;
import com.jake.advance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JedisPool jedisPool;

    public String getUserEmail(Long id) {
//        try(var jedisPool = new JedisPool("127.0.0.1", 6379)) {
//            try (Jedis jedis = jedisPool.getResource()) {
//                var userEmailRedisKey = "users:%d:email".formatted(id);
//
//                // 1. request to cache
//                String userEmail = jedis.get(userEmailRedisKey);
//                if (userEmail != null) return userEmail;
//
//                // 2. else db
//                userEmail = userRepository.findById(id).orElse(User.builder().build()).getEmail();
//
//                // 3. cache
//                jedis.set(userEmailRedisKey, userEmail);
//
//                return userEmail;
//            }
//        }

        try (Jedis jedis = jedisPool.getResource()) {
            var userEmailRedisKey = "users:%d:email".formatted(id);

            // 1. request to cache
            String userEmail = jedis.get(userEmailRedisKey);
            if (userEmail != null) return userEmail;

            // 2. else db
            userEmail = userRepository.findById(id).orElse(User.builder().build()).getEmail();

            // 3. cache
//            jedis.set(userEmailRedisKey, userEmail);
            jedis.setex(userEmailRedisKey, 30, userEmail);

            return userEmail;
        }
    }
}
