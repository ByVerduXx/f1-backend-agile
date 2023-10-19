package com.uah.f1backend.repository;

import com.uah.f1backend.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {}
