package ru.capybara.springboot.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomErrorResponse {
    String message;
    String errorDescription;
    HttpStatus status;
    LocalDateTime time;
}
