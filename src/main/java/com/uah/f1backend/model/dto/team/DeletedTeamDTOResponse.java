package com.uah.f1backend.model.dto.team;

import lombok.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class DeletedTeamDTOResponse {
    private final String message;
    private final String teamName;
}
