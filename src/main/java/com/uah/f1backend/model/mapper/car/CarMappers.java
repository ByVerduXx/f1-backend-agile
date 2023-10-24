package com.uah.f1backend.model.mapper.car;

import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.dto.car.CarDTORequest;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import java.util.List;

public class CarMappers {

    public static CarModel toCarModel(CarDTORequest carDTORequest) {
        try {
            final CarModel carModel = new CarModel();

            carModel.setName(carDTORequest.getName());
            carModel.setCode(carDTORequest.getCode());
            carModel.setErsGainSlow(carDTORequest.getErsGainSlow());
            carModel.setErsGainMedium(carDTORequest.getErsGainMedium());
            carModel.setErsGainFast(carDTORequest.getErsGainFast());
            carModel.setConsumption(carDTORequest.getConsumption());

            return carModel;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static CarDTOResponse toCarDTOResponse(CarModel carModel) {
        try {
            return new CarDTOResponse(
                    carModel.getId(),
                    carModel.getName(),
                    carModel.getCode(),
                    carModel.getErsGainSlow(),
                    carModel.getErsGainMedium(),
                    carModel.getErsGainFast(),
                    carModel.getConsumption(),
                    carModel.getTeam().getName());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static List<CarDTOResponse> toCarDTOResponses(List<CarModel> carModels) {
        return carModels.stream().map(CarMappers::toCarDTOResponse).toList();
    }
}
