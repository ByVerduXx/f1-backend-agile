package com.uah.f1backend.repository;

import com.uah.f1backend.model.DriverModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverModelRepository extends JpaRepository<DriverModel, Integer> {
    Optional<DriverModel> findByName(String name);
}
