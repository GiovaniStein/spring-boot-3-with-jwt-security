package com.giovani.springbootjwt.service;

import com.giovani.springbootjwt.dto.UserRegisterDto;
import com.giovani.springbootjwt.enums.Role;
import com.giovani.springbootjwt.model.User;
import com.giovani.springbootjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegisterDto request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
    }

}
