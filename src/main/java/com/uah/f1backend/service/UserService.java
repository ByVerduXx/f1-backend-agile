package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.UserModel;
import com.uah.f1backend.model.dto.user.*;
import com.uah.f1backend.model.mapper.user.UserMappers;
import com.uah.f1backend.repository.RoleModelRepository;
import com.uah.f1backend.repository.UserModelRepository;
import com.uah.f1backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserModelRepository userRepository;
    private final RoleModelRepository roleModelRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserDTOResponse> getAllUsers() {
        return UserMappers.toUserDTOResponse(userRepository.findAll());
    }

    public UserDTOResponse getUserById(Integer id) {
        final var c = userRepository.findById(id).orElseThrow(HttpExceptions.UserDoesntExist::new);
        return UserMappers.toUserDTOResponse(c);
    }

    public UserDTOResponse insertUser(UserDTORequest user) {
        final var cm = UserMappers.toUserModel(user);
        cm.setPassword(bCryptPasswordEncoder.encode(cm.getPassword()));
        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new HttpExceptions.UsernameInUseException();
        }

        final var role = roleModelRepository
                .findById(user.getRoleId())
                .orElseThrow(HttpExceptions.RoleDoesntExistException::new);

        cm.setRole(role);
        cm.setValidated(false);

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

        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new HttpExceptions.UsernameInUseException();
        }

        c.setEmail(cm.getEmail());
        c.setName(cm.getName());
        c.setLastname(cm.getLastname());
        c.setUsername(cm.getUsername());
        if (!Objects.equals(c.getRole().getId(), user.getRoleId())) {
            final var role = roleModelRepository
                    .findById(user.getRoleId())
                    .orElseThrow(HttpExceptions.RoleDoesntExistException::new);
            c.setRole(role);
        }

        return UserMappers.toUserDTOResponse(userRepository.save(c));
    }

    //Admin function
    public ChangePasswordUserDTOResponse changePasswordUserByID(Integer userId, ChangePasswordUserDTORequest request){
        var user = userRepository.findById(userId).orElseThrow(HttpExceptions.UserDoesntExist::new);

        return getChangePasswordUserDTOResponse(request, user);
    }

    //Users function
    public ChangePasswordUserDTOResponse changePasswordUserAuthenticated(ChangePasswordUserDTORequest request){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findById(userDetails.getId()).orElseThrow(HttpExceptions.UserDoesntExist::new);

        return getChangePasswordUserDTOResponse(request, user);
    }

    public List<UserDTOResponse> findAllValidatePendingUsers() {
        return UserMappers.toUserDTOResponse(userRepository.findAllByValidated(false));
    }

    public String validateUser(Integer id) {
        UserModel user = userRepository.findById(id).orElseThrow(HttpExceptions.UserDoesntExist::new);
        user.setValidated(true);
        userRepository.save(user);
        return "User validated";
    }

    private ChangePasswordUserDTOResponse getChangePasswordUserDTOResponse(ChangePasswordUserDTORequest request, UserModel user) {
        if (!bCryptPasswordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return new ChangePasswordUserDTOResponse(user.getId(), user.getUsername(), false);
        }

        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return new ChangePasswordUserDTOResponse(user.getId(), user.getUsername(), true);
    }

}
