package com.uah.f1backend.repository;


import com.uah.f1backend.model.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryModelRepository extends JpaRepository<CountryModel, Integer> {
}
