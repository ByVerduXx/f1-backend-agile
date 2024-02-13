package com.uah.f1backend.model.dto.user;

import com.uah.f1backend.model.dto.role.RoleDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String username;
    private RoleDTOResponse role;
    private Boolean validated;
}
