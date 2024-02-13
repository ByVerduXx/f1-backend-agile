package com.uah.f1backend.model.dto.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamDTORequest {
    private String name;
    private String logo;
    private String twitter;
}
