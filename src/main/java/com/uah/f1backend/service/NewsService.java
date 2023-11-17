package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.NewsModel;
import com.uah.f1backend.model.dto.news.NewsDTORequest;
import com.uah.f1backend.model.dto.news.NewsDTOResponse;
import com.uah.f1backend.model.dto.news.NewsDTOResponse;
import com.uah.f1backend.model.mapper.news.NewsMappers;
import com.uah.f1backend.model.mapper.news.NewsMappers;
import com.uah.f1backend.repository.NewsModelRepository;
import com.uah.f1backend.repository.NewsModelRepository;
import com.uah.f1backend.repository.TeamModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsModelRepository newsModelRepository;

    public List<NewsDTOResponse> getAllNews() {
        return NewsMappers.toNewsDTOResponses(newsModelRepository.findAll());
    }

    public NewsDTOResponse getNewsById(Integer id) {
        return NewsMappers.toNewsDTOResponse(
                newsModelRepository.findById(id).orElseThrow(HttpExceptions.NewsDoesntExistException::new));
    }

    public NewsDTOResponse saveNews(NewsDTORequest newsDTORequest) {
        try {
            NewsModel newsModel = NewsMappers.toNewsModel(newsDTORequest);

            isNewsPermalinkInUse(newsModel.getPermalink());
            isNewsTitleInUse(newsModel.getTitle());
            validateNewsFields(newsModel);
            return NewsMappers.toNewsDTOResponse(newsModelRepository.save(newsModel));
        } catch (NullPointerException e) {
            throw new HttpExceptions.NewsNotSavedException();
        }
    }

    public NewsDTOResponse updateNews(Integer id, NewsDTORequest newsDTORequest) {
        try {
            NewsModel newsModel =
                    newsModelRepository.findById(id).orElseThrow(HttpExceptions.NewsDoesntExistException::new);


            isNewsPermalinkInUse(newsModel.getPermalink());
            isNewsTitleInUse(newsModel.getTitle());

            newsModel.setTitle(newsDTORequest.getTitle());
            newsModel.setPermalink(newsDTORequest.getPermalink());
            newsModel.setImage(newsDTORequest.getImage());
            newsModel.setText(newsDTORequest.getText());
            newsModel.setPublication_date(newsDTORequest.getPublication_date());

            validateNewsFields(newsModel);

            return NewsMappers.toNewsDTOResponse(newsModelRepository.save(newsModel));
        } catch (NullPointerException e) {
            throw new HttpExceptions.NewsNotSavedException();
        }
    }

    public String deleteNews(Integer id) {
        NewsModel newsModel = newsModelRepository.findById(id).orElseThrow(HttpExceptions.NewsDoesntExistException::new);
        newsModelRepository.delete(newsModel);
        return "News with id " + id + " and title " + newsModel.getTitle() + " has been deleted";
    }

    private void validateNewsFields(NewsModel news) {
        if (news.getTitle() == null || news.getTitle().isEmpty()) {
            throw new HttpExceptions.NewsTitleNotValidException();
        }
        if (news.getPermalink() == null || news.getPermalink().isEmpty()) {
            throw new HttpExceptions.NewsPermalinkNotValidException();
        }

    }

    private void isNewsPermalinkInUse(String permalink) {
        if (newsModelRepository.findNewsModelByPermaLink(permalink).isPresent()) {
            throw new HttpExceptions.NewsPermalinkInUseException();
        }
    }

    private void isNewsTitleInUse(String title) {
        if (newsModelRepository.findNewsModelByTitle(title).isPresent()) {
            throw new HttpExceptions.NewsTitleInUseException();
        }
    }
}
