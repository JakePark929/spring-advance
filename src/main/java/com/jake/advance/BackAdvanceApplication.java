package com.jake.advance;

import com.jake.advance.domain.User;
import com.jake.advance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class BackAdvanceApplication implements ApplicationRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackAdvanceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        userRepository.save(User.builder().name("jake").email("jake@email.com").build());
//        userRepository.save(User.builder().name("tony").email("tony@email.com").build());
//        userRepository.save(User.builder().name("bob").email("bob@email.com").build());
//        userRepository.save(User.builder().name("ryan").email("ryan@email.com").build());
    }
}
