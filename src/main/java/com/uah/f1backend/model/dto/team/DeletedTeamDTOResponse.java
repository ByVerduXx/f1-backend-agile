package com.uah.f1backend.model.dto.team;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class DeletedTeamDTOResponse {
    private final String message;
    private final String teamName;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeletedTeamDTOResponse)) {
            return false;
        }
        DeletedTeamDTOResponse deletedTeamDTOResponse = (DeletedTeamDTOResponse) obj;

        return  deletedTeamDTOResponse.getTeamName().equals(this.teamName)
                && deletedTeamDTOResponse.getMessage().equals(this.message);
    }
}
