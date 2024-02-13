package com.uah.f1backend.model.dto.circuit;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CircuitDTOResponse {
    private Integer id;
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
