package com.giovani.springbootjwt.controller;

import com.giovani.springbootjwt.dto.ResponseDto;
import com.giovani.springbootjwt.dto.UserRegisterDto;
import com.giovani.springbootjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<ResponseDto> register(@RequestBody UserRegisterDto request) {
        try {
            service.register(request);
            return ResponseEntity.ok(ResponseDto.builder().message("User created").build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.builder().message(ExceptionUtils.getRootCauseMessage(e)).build());
        }

    }

}
