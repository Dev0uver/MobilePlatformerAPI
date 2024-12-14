package ru.capybara.springboot.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.capybara.springboot.exception.CustomErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestRuntimeExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<CustomErrorResponse> handleConflict(RuntimeException ex, WebRequest request) {
        CustomErrorResponse error = CustomErrorResponse.builder()
                .message(ex.getMessage())
                .time(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST)
                .errorDescription("Runtime exception")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
