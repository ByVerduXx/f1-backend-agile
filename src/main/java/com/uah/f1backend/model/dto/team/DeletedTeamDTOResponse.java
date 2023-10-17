package com.uah.f1backend.model.dto.team;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class DeletedTeamDTOResponse {
    private final String message;
    private final String teamName;
}
