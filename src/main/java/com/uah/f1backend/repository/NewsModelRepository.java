package com.uah.f1backend.repository;

import com.uah.f1backend.model.NewsModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsModelRepository extends JpaRepository<NewsModel, Integer> {
    Optional<NewsModel> findNewsModelByTitle(String title);

    Optional<NewsModel> findNewsModelByPermalink(String permalink);
}
