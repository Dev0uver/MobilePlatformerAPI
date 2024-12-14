package ru.capybara.springboot.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailIsBusyException extends CustomException {
    final static String message = "Email %s is busy";
    final static String errorDescription = "user.exception.email-is-busy";
    final static HttpStatus status = HttpStatus.BAD_REQUEST;
    public EmailIsBusyException(String email) {
        super(String.format(message, email), errorDescription, status);
    }
}
