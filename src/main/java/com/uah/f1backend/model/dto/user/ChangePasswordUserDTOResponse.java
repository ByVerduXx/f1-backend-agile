package com.uah.f1backend.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ChangePasswordUserDTOResponse {
    private final Integer id;
    private final String username;
    private final Boolean passwordIsChanged;
}
