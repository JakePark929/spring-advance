package com.jake.advance.lettuce.service;

import com.jake.advance.lettuce.domain.RedisHashUser;
import com.jake.advance.lettuce.domain.User;
import com.jake.advance.lettuce.repository.RedisHashUserRepository;
import com.jake.advance.lettuce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.jake.advance._core.config.CacheConfig.CACHE1;

@RequiredArgsConstructor
@Service
public class UserService {
//    private final UserRepository userRepository;
//    private final RedisHashUserRepository redisHashUserRepository;
//    private final RedisTemplate<String, User> userRedisTemplate;
//    private final RedisTemplate<String, Object> objectRedisTemplate;
//
//    public User getUser(Long id) {
//        var key = "users:%d".formatted(id);
//
//        // 1. cache get
//        var cachedUser = objectRedisTemplate.opsForValue().get(key);
//        if (cachedUser != null) {
//            return (User) cachedUser;
//        }
//
//        // 2. else dv -> cache set
//        final User user = userRepository.findById(id).orElseThrow();
//        objectRedisTemplate.opsForValue().set(key, user, Duration.ofSeconds(10));
//
//        // 3. return
//        return user;
//    }
//
//    public RedisHashUser getRedisHashUser(Long id) {
//        return redisHashUserRepository.findById(id).orElseGet(() -> {
//            final User user = userRepository.findById(id).orElseThrow();
//
//            return redisHashUserRepository.save(RedisHashUser.builder()
//                    .id(user.getId())
//                    .name(user.getName())
//                    .email(user.getEmail())
//                    .createdAt(user.getCreatedAt())
//                    .updatedAt(user.getUpdatedAt())
//                    .build());
//        });
//    }
//
//    @Cacheable(cacheNames = CACHE1, key = "'user:' + #id")
//    public User getSpringCacheUser(Long id) {
//        return userRepository.findById(id).orElseThrow();
//    }
}
