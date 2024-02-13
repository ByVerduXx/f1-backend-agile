package com.uah.f1backend.model.dto.team;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTOResponse {
    private Integer id;
    private String name;
    private String logo;
    private String twitter;
}
