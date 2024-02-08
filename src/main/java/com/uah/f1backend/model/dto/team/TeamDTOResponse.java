package com.uah.f1backend.model.dto.team;

import com.uah.f1backend.model.dto.user.UserDTOResponse;
import lombok.*;

import java.util.List;

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
