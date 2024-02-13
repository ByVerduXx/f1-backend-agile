package com.uah.f1backend.model.dto.team;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeletedTeamDTOResponse {
    private String message;
    private String teamName;
}
