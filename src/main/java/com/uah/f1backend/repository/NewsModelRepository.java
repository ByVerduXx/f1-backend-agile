package com.uah.f1backend.repository;

import com.uah.f1backend.model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsModelRepository extends JpaRepository<NewsModel, Integer> {
    Optional<NewsModel> findNewsModelByTitle(String title);

    Optional<NewsModel> findNewsModelByPermalink(String permalink);
}
