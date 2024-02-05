package com.uah.f1backend.model.mapper.news;

import com.uah.f1backend.model.NewsModel;
import com.uah.f1backend.model.dto.news.NewsDTORequest;
import com.uah.f1backend.model.dto.news.NewsDTOResponse;
import java.util.List;

public class NewsMappers {

    public static NewsModel toNewsModel(NewsDTORequest newsDTORequest) {
        try {
            final NewsModel newsModel = new NewsModel();

            newsModel.setTitle(newsDTORequest.getTitle());
            newsModel.setPermalink(newsDTORequest.getPermalink());
            newsModel.setImage(newsDTORequest.getImage());
            newsModel.setText(newsDTORequest.getText());
            newsModel.setPublicationDate(newsDTORequest.getPublicationDate());

            return newsModel;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static NewsDTOResponse toNewsDTOResponse(NewsModel newsModel) {
        try {
            return new NewsDTOResponse(
                    newsModel.getId(),
                    newsModel.getPermalink(),
                    newsModel.getTitle(),
                    newsModel.getImage(),
                    newsModel.getText(),
                    newsModel.getPublicationDate());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static List<NewsDTOResponse> toNewsDTOResponses(List<NewsModel> newsModels) {
        return newsModels.stream().map(NewsMappers::toNewsDTOResponse).toList();
    }
}
