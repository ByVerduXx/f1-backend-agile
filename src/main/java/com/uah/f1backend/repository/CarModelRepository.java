package com.uah.f1backend.repository;

import com.uah.f1backend.model.CarModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    Optional<CarModel> findCarModelByName(String name);

    Optional<CarModel> findCarModelByCode(String code);

    List<CarModel> findAllByTeamId(Integer teamId);
}
