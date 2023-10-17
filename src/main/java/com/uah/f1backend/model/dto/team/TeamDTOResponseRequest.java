package com.uah.f1backend.model.dto.team;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TeamDTOResponseRequest {
    private final String name;
    private final String logo;
    private final String twitter;
}
