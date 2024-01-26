package com.jake.advance.lettuce.controller;

import com.jake.advance.lettuce.domain.RedisHashUser;
import com.jake.advance.lettuce.domain.User;
import com.jake.advance.lettuce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/redishash-users/{id}")
    public RedisHashUser getRedisHashUser(@PathVariable Long id) { return userService.getRedisHashUser(id); }

    @GetMapping("/cache-users/{id}")
    public User getSpringCacheUser(@PathVariable Long id) {
        return userService.getSpringCacheUser(id);
    }
}
