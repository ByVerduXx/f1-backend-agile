package com.uah.f1backend.repository;

import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.RaceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RaceModelRepository extends JpaRepository<RaceModel, Integer> {
}
