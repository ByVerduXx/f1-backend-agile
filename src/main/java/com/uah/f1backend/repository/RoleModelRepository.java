package com.uah.f1backend.repository;

import com.uah.f1backend.model.RoleModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleModelRepository extends JpaRepository<RoleModel, Integer> {
    Optional<RoleModel> findByRoleName(String name);
}
