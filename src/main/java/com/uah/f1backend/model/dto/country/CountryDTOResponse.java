package com.uah.f1backend.model.dto.country;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTOResponse {
    private Integer id;
    private String code;
    private String name;
}
