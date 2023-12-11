package com.uah.f1backend.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDTORequest {
    private final String email;
    private final String password;
    private final String name;
    private final String lastname;
    private final String username;
    private final Integer roleId;
    private final Boolean validated;
}
