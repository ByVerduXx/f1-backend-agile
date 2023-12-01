package com.uah.f1backend.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDTOResponse {
    private final Integer id;
    private final String email;
    private final String password;
    private final String name;
    private final String lastname;
    private final String username;
    private final Integer role;
    private final Boolean validated;
}
