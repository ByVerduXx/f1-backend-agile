package com.uah.f1backend.model.dto.team;

import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class TeamDetailDTOResponse {
    private final Integer id;
    private final String name;
    private final String logo;
    private final String twitter;
    private final List<DriverDTOResponse> drivers;
    private final List<CarDTOResponse> cars;
}
