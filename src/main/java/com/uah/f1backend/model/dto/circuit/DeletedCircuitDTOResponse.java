package com.uah.f1backend.model.dto.circuit;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeletedCircuitDTOResponse {
    private String message;
    private String circuitName;
}
