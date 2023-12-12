package com.uah.f1backend.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ChangePasswordUserDTORequest {
    private final String oldPassword;
    private final String newPassword;
}
