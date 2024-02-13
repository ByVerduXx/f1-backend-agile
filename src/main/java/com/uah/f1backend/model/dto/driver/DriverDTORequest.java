package com.uah.f1backend.model.dto.driver;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class DriverDTORequest {

    private String name;

    private String lastName;

    private String initial;

    private Integer dorsal;

    private String photo;

    private String twitter;

    private Integer idCountry;
    private Integer idTeam;
}
