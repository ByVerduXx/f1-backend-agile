package com.uah.f1backend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTORequest {
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String username;
    private Integer roleId;
}
