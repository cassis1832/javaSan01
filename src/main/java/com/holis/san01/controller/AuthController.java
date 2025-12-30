package com.holis.san01.controller;

import com.holis.san01.dto.LoginRequest;
import com.holis.san01.dto.LoginResponse;
import com.holis.san01.dto.TokenResponse;
import com.holis.san01.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = authService.login(request.email(), request.password());
        return LoginResponse.bearer(tokenResponse);
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@RequestBody String refreshToken) {
        TokenResponse tokenResponse = authService.refresh(refreshToken);
        return LoginResponse.bearer(tokenResponse);
    }

//    @PostMapping("/logoff")
//    public void logoff(@RequestBody String refreshToken) {
//        TokenResponse tokenResponse = authService.logoff(refreshToken);
//    }
}
