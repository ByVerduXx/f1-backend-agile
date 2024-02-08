package com.uah.f1backend.model.dto.team;

import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import java.util.List;

import com.uah.f1backend.model.dto.user.UserDTOResponse;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TeamDetailDTOResponse {
    private Integer id;
    private String name;
    private String logo;
    private String twitter;
    private List<DriverDTOResponse> drivers;
    private List<CarDTOResponse> cars;
    private List<UserDTOResponse> managers;
}
