package com.uah.f1backend.model.dto.driver;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class DeletedDriverDTOResponse {
    private final String message;
    private final Integer id;
}
