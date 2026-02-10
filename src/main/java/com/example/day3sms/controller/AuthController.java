package com.example.day3sms.controller;


import com.example.day3sms.dto.LoginRequestDto;
import com.example.day3sms.dto.RegisterRequestDto;
import com.example.day3sms.dto.TokenResponseDto;
import com.example.day3sms.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return service.login(loginRequestDto);
    }
    @PostMapping("/register")
    public TokenResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
        return service.register(registerRequestDto);
    }
}
