package com.uah.f1backend.model.dto.team;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class TeamDTOResponse {
    private final Integer id;
    private final String name;
    private final String logo;
    private final String twitter;
}
