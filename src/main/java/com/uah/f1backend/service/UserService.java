package com.uah.f1backend.service;

import com.uah.f1backend.model.dto.User.UserDTOResponse;
import com.uah.f1backend.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserModelRepository userRepository;

    public List<UserDTOResponse> getAllUsers() {
        return UserMappers;
    }
}
