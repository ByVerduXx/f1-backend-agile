package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.user.*;
import com.uah.f1backend.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTOResponse> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> insertUser(@RequestBody UserDTORequest user) {
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeletedUserDTOResponse> deleteUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTOResponse> updateUserById(@PathVariable Integer id, @RequestBody UserDTORequest user) {
        return new ResponseEntity<>(userService.updateUserById(id, user), HttpStatus.CREATED);
    }

    @PostMapping("changePassword/{id}")
    public ResponseEntity<ChangePasswordUserDTOResponse> changePassword(
            @PathVariable Integer id, @RequestBody ChangePasswordUserDTORequest request) {
        return ResponseEntity.ok(userService.changePasswordUserByID(id, request));
    }

    @PostMapping("changePassword")
    public ResponseEntity<ChangePasswordUserDTOResponse> changePassword(
            @RequestBody ChangePasswordUserDTORequest request) {
        return ResponseEntity.ok(userService.changePasswordUserAuthenticated(request));
    }

    @GetMapping("validate")
    public ResponseEntity<List<UserDTOResponse>> validateUser() {
        return ResponseEntity.ok(userService.findAllValidatePendingUsers());
    }

    @PostMapping("validate/{id}")
    public ResponseEntity<String> validateUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.validateUser(id));
    }

    @GetMapping("freeManagers")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<List<UserDTOResponse>> getFreeManagers() {
        return ResponseEntity.ok(userService.findAllManagersWithoutTeam());
    }

    @GetMapping("teamManagers/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<List<UserDTOResponse>> getTeamManagers(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findAllManagersByTeamId(id));
    }
}
