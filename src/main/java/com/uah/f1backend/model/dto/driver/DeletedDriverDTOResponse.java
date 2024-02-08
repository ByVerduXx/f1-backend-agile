package com.uah.f1backend.model.dto.driver;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeletedDriverDTOResponse {
    private String message;
    private Integer id;
}
