package com.abhi.flogg.service;

import com.abhi.flogg.entity.User;
import com.abhi.flogg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .orElseGet(() -> userRepository.save(user));
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(User.builder()
                        .email(email)
                        .password("password")
                        .fullName("Flog Admin")
                        .isActive(true)
                        .build()
                ));
    }

    @Transactional
    public User getDefaultHelperUser() {
        return getUserByEmail("admin@flogg.com");
    }
}