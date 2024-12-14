package ru.capybara.springboot.exception;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotFoundException extends CustomException {
    static String massage = "User with id=%d not found.";
    static String massageWithoutId = "User not found.";
    static String errorDescription = "user.exception.not-found";
    static HttpStatus status = HttpStatus.NOT_FOUND;
    public UserNotFoundException(int userId) {
        super(String.format(massage, userId), errorDescription, status);
    }

    public UserNotFoundException() {
        super(massageWithoutId, errorDescription, status);
    }
}
