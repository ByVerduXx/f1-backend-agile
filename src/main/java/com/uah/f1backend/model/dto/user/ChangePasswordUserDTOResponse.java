package com.uah.f1backend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangePasswordUserDTOResponse {
    private Integer id;
    private String username;
    private Boolean passwordIsChanged;
}
