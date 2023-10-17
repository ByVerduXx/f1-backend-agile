package com.uah.f1backend.model.dto.team;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TeamDTORequest {
    private final String name;
    private final String logo;
    private final String twitter;
}
