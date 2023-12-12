package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.user.*;
import com.uah.f1backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<UserDTOResponse> insertUser(UserDTORequest user) {
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeletedUserDTOResponse> deleteUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTOResponse> updateUserById
            (@PathVariable Integer id, @RequestBody UserDTORequest user) {
        return new ResponseEntity<>(userService.updateUserById(id, user), HttpStatus.CREATED);
    }

    @GetMapping("changePassword/{id}")
    public ResponseEntity<ChangePasswordUserDTOResponse> changePassword(@PathVariable Integer id, @RequestBody ChangePasswordUserDTORequest request) {
        return ResponseEntity.ok(userService.changePasswordUserByID(id, request));
    }
}
