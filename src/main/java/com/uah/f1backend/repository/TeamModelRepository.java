package com.uah.f1backend.repository;

import com.uah.f1backend.model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamModelRepository extends JpaRepository<TeamModel, Long> {
    // Find a team in db matching the given name
    Optional<TeamModel> findByName(String name);
}