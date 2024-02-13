package com.uah.f1backend.security;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.repository.UserModelRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserModelRepository userRepository;
    private final JWTService jwtService;

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository
                .findFirstByUsername(request.getUsername())
                .orElseThrow(HttpExceptions.UserDoesntExist::new);
        var jwt = jwtService.generateToken(
                Map.of("username", user.getUsername(), "role", user.getRole().getRoleName()),
                new UserDetailsImpl(user));
        return AuthenticationResponse.builder().jwt(jwt).build();
    }

    public boolean isValidToken(String token) {
        return jwtService.isValidToken(token);
    }
}
