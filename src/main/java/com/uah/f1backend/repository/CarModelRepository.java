package com.uah.f1backend.repository;

import com.uah.f1backend.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    Optional<CarModel> findCarModelByName(String name);
    Optional<CarModel> findCarModelByCode(String code);
}
