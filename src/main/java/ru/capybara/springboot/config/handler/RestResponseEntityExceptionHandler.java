package ru.capybara.springboot.config.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.capybara.springboot.exception.CustomException;
import ru.capybara.springboot.exception.CustomErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<CustomErrorResponse> handleConflict(CustomException ex, WebRequest request) {
        CustomErrorResponse error = CustomErrorResponse.builder()
                .message(ex.getMessage())
                .errorDescription(ex.getErrorDescription())
                .status(ex.getStatus())
                .time(LocalDateTime.now().toString())
                .build();
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}
