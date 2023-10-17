package com.uah.f1backend.model.dto.team;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class TeamDTOResponse {
    private final Integer id;
    private final String name;
    private final String logo;
    private final String twitter;
}
