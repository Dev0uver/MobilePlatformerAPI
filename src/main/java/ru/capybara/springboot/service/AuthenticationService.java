package ru.capybara.springboot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.capybara.springboot.dto.AuthRequest;
import ru.capybara.springboot.dto.AuthenticationResponse;
import ru.capybara.springboot.dto.RegisterRequest;
import ru.capybara.springboot.exception.EmailIsBusyException;
import ru.capybara.springboot.exception.UserNotFoundException;
import ru.capybara.springboot.exception.UsernameIsBusyException;
import ru.capybara.springboot.model.User;
import ru.capybara.springboot.repository.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthenticationResponse auth(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                UserNotFoundException::new
        );
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .nickname(user.getNickname())
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailIsBusyException(request.getEmail());
        }
        if (userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new UsernameIsBusyException(request.getNickname());
        }
        User user = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .nickname(user.getNickname())
                .build();
    }
}
