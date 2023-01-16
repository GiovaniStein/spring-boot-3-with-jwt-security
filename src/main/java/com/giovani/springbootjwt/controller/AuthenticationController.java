package com.giovani.springbootjwt.controller;

import com.giovani.springbootjwt.dto.LoginDto;
import com.giovani.springbootjwt.dto.ResponseDto;
import com.giovani.springbootjwt.dto.TokenDto;
import com.giovani.springbootjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<ResponseDto> authenticate(@RequestBody LoginDto request) {

        try {
            TokenDto authenticate = service.authenticate(request);
            return ResponseEntity.ok(ResponseDto.builder().data(authenticate).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.builder().message(ExceptionUtils.getRootCauseMessage(e)).build());
        }

    }


}
