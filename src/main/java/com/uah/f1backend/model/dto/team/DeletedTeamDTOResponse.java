package com.uah.f1backend.model.dto.team;

import lombok.*;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class DeletedTeamDTOResponse {
    private final String message;
    private final String teamName;
}
