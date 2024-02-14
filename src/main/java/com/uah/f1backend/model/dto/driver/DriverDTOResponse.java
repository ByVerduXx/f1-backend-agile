package com.uah.f1backend.model.dto.driver;

import com.uah.f1backend.model.dto.country.CountryDTOResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class DriverDTOResponse {

    private Integer id;

    private String name;

    private String lastName;

    private String initial;

    private Integer dorsal;

    private String photo;

    private String twitter;

    private CountryDTOResponse country;

    private Integer idTeam;
}
