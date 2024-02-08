package com.uah.f1backend.model.dto.circuit;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class CircuitDTORequest {
    private String name;
    private String city;
    private Integer id_country;
    private String image;
    private Integer laps;
    private Integer length;
    private Integer slow_turns;
    private Integer medium_turns;
    private Integer fast_turns;
}
