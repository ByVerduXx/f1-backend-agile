package com.uah.f1backend.repository;


import com.uah.f1backend.model.CircuitModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CircuitModelRepository extends JpaRepository<CircuitModel, Long> {
    //Find a circuit by its name
    Optional<CircuitModel> findByName(String name);
}
