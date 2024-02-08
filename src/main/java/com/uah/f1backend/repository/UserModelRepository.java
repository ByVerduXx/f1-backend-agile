package com.uah.f1backend.repository;

import com.uah.f1backend.model.UserModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findFirstByUsername(String username);

    List<UserModel> findAllByValidated(Boolean validated);

    List<UserModel> findAllByRole_IdAndTeamIsNull(Integer roleId);
}
