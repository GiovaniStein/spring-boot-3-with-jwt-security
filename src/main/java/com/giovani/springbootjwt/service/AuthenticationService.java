package com.giovani.springbootjwt.service;

import com.giovani.springbootjwt.dto.LoginDto;
import com.giovani.springbootjwt.dto.TokenDto;
import com.giovani.springbootjwt.repository.UserRepository;
import com.giovani.springbootjwt.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenDto authenticate(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return TokenDto.builder()
                .token(jwtToken)
                .type("Bearer")
                .build();
    }
}
