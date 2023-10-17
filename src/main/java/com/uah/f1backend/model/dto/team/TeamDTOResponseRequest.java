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

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TeamDTOResponseRequest)) {
            return false;
        }
        TeamDTOResponseRequest teamDTOResponseRequest = (TeamDTOResponseRequest) obj;

        return teamDTOResponseRequest.getName().equals(this.name)
                && teamDTOResponseRequest.getLogo().equals(this.logo)
                && teamDTOResponseRequest.getTwitter().equals(this.twitter);
    }
}
