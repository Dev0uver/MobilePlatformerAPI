package ru.capybara.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.capybara.springboot.exception.EmailIsBusyException;
import ru.capybara.springboot.model.User;

@RestController
@RequestMapping("/api/dev") //http://localhost:8080/api/dev
public class DevController {
    @GetMapping
    public ResponseEntity<User> getCurrentUserData() {
        return ResponseEntity.ok((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @GetMapping("/exception")
    public String testException() {
        throw new EmailIsBusyException("this.email");
    }
}
