package ru.capybara.springboot.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.capybara.springboot.dto.AuthRequest;
import ru.capybara.springboot.dto.AuthenticationResponse;
import ru.capybara.springboot.dto.RegisterRequest;
import ru.capybara.springboot.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/security") //http://localhost:8080/api/security
public class AuthController {

    AuthenticationService authenticationService;
    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthRequest request) {
        return ResponseEntity.ok().body(authenticationService.auth(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok().body(authenticationService.register(request));
    }
}
