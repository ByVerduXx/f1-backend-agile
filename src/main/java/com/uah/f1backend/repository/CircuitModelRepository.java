package com.uah.f1backend.repository;

import com.uah.f1backend.model.CircuitModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircuitModelRepository extends JpaRepository<CircuitModel, Integer> {
    // Find a circuit by its name
    Optional<CircuitModel> findByName(String name);
}
