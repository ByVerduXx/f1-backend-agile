package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.dto.user.*;
import com.uah.f1backend.model.mapper.user.UserMappers;
import com.uah.f1backend.repository.RoleModelRepository;
import com.uah.f1backend.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserModelRepository userRepository;
    private final RoleModelRepository roleModelRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserDTOResponse> getAllUsers() {
        return UserMappers.toUserDTOResponse(userRepository.findAll());
    }

    public UserDTOResponse getUserById(Integer id) {
        final var c = userRepository.findById(id).orElseThrow(HttpExceptions.UserDoesntExist::new);
        return UserMappers.toUserDTOResponse(c);
    }

    public UserDTOResponse insertUser(UserDTORequest user) {
        final var cm = UserMappers.toUserModel(user);

        if (cm == null) {
            throw new HttpExceptions.UserNotSavedException();
        }

        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new HttpExceptions.UsernameInUseException();
        }

        final var role = roleModelRepository
                .findById(user.getRoleId())
                .orElseThrow(HttpExceptions.RoleDoesntExistException::new);

        cm.setRole(role);

        return UserMappers.toUserDTOResponse(userRepository.save(cm));
    }

    public DeletedUserDTOResponse deleteUserById(Integer id) {
        final var c = userRepository.findById(id).orElseThrow(HttpExceptions.UserDoesntExist::new);
        userRepository.deleteById(id);
        return new DeletedUserDTOResponse(c.getUsername(),"User deleted");
    }

    public UserDTOResponse updateUserById(Integer id, UserDTORequest user) {
        final var c = userRepository.findById(id).orElseThrow(HttpExceptions.UserDoesntExist::new);
        final var cm = UserMappers.toUserModel(user);

        if (cm == null) {
            throw new HttpExceptions.UserNotSavedException();
        }

        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new HttpExceptions.UsernameInUseException();
        }

        c.setEmail(cm.getEmail());
        c.setPassword(cm.getPassword());
        c.setName(cm.getName());
        c.setLastname(cm.getLastname());
        c.setUsername(cm.getUsername());
        c.setRole(cm.getRole());
        c.setValidated(cm.getValidated());

        return UserMappers.toUserDTOResponse(userRepository.save(c));
    }

    public ChangePasswordUserDTOResponse changePasswordUserByID(Integer userId, ChangePasswordUserDTORequest request){
        var user = userRepository.findById(userId).orElseThrow(HttpExceptions.UserDoesntExist::new);

        if (!user.getPassword().equals(bCryptPasswordEncoder.encode(request.getOldPassword()))){
            return new ChangePasswordUserDTOResponse(user.getId(), user.getUsername(), false);
        }

        var encodedPassword = encodePassword(request.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return new ChangePasswordUserDTOResponse(user.getId(), user.getUsername(), true);
    }

    public String encodePassword(String password) {
        if (bCryptPasswordEncoder == null) {
            bCryptPasswordEncoder = new BCryptPasswordEncoder();
        }
        return bCryptPasswordEncoder.encode(password);
    }



}
