package com.uah.f1backend.model.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleDTOResponse {
    private  Integer id;
    private String roleName;
}
