package com.uah.f1backend.model.dto.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DeletedUserDTOResponse {
    private final String username;
    private final String message;
}
