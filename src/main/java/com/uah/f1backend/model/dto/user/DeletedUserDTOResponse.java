package com.uah.f1backend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeletedUserDTOResponse {
    private String username;
    private String message;
}
