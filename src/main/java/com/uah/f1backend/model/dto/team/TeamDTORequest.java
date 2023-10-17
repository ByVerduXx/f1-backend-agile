package com.uah.f1backend.model.dto.team;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TeamDTORequest {
    private final String name;
    private final String logo;
    private final String twitter;
}
