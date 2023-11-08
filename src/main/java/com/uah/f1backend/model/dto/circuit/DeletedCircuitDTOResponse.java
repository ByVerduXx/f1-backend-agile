package com.uah.f1backend.model.dto.circuit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class DeletedCircuitDTOResponse {
    private final String message;
    private final String circuitName;
}
