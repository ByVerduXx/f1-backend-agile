package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.UserModel;
import com.uah.f1backend.repository.UserModelRepository;
import com.uah.f1backend.security.UserDetailsImpl;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private UserModelRepository userModelRepository;

    public Optional<UserDetailsImpl> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of((UserDetailsImpl) authentication.getPrincipal());
    }

    public UserModel getUserAuthenticated() {
        return userModelRepository
                .findById(getUserDetails()
                        .orElseThrow(HttpExceptions.UnauthorizedException::new)
                        .getId())
                .orElseThrow(HttpExceptions.UserDoesntExist::new);
    }
}
