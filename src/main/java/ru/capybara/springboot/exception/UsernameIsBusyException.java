package ru.capybara.springboot.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsernameIsBusyException extends CustomException {
    static String message = "Username %s is busy";
    static String errorDescription = "user.exception.username-is-busy";
    static HttpStatus status = HttpStatus.BAD_REQUEST;
    public UsernameIsBusyException(String email) {
        super(String.format(message, email), errorDescription, status);
    }
}
