package com.uah.f1backend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangePasswordUserDTORequest {
    private String oldPassword;
    private String newPassword;
}
