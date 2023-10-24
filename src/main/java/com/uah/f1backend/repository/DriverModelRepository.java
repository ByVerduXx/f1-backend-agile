package com.uah.f1backend.repository;

import com.uah.f1backend.model.DriverModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverModelRepository extends JpaRepository<DriverModel, Integer> {
    Optional<DriverModel> findByDorsal(Integer dorsal);
}
