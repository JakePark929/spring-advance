package com.jake.advance.controller;

import com.jake.advance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/{id}/email")
    public String getUserEmail(@PathVariable Long id) {
        return userService.getUserEmail(id);
    }
}
