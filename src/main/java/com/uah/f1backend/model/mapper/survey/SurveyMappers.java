package com.uah.f1backend.model.mapper.survey;

import com.uah.f1backend.model.SurveyModel;
import com.uah.f1backend.model.dto.survey.SurveyDTORequest;
import com.uah.f1backend.model.dto.survey.SurveyDTOResponse;
import com.uah.f1backend.model.mapper.driver.DriverMappers;
import com.uah.f1backend.model.mapper.vote.VoteMappers;
import java.util.List;

public class SurveyMappers {
    public static SurveyModel toSurveyModel(SurveyDTORequest surveyDTORequest) {
        try {
            final var surveyModel = new SurveyModel();
            surveyModel.setPermalink(surveyDTORequest.getPermalink());
            surveyModel.setTitle(surveyDTORequest.getTitle());
            surveyModel.setDescription(surveyDTORequest.getDescription());
            surveyModel.setLimitDate(surveyDTORequest.getLimitDate());
            return surveyModel;
        } catch (NullPointerException npe) {
            throw new NullPointerException();
        }
    }

    public static SurveyDTOResponse toSurveyDTOResponse(SurveyModel surveyModel) {
        try {
            return new SurveyDTOResponse(
                    surveyModel.getId(),
                    surveyModel.getPermalink(),
                    surveyModel.getTitle(),
                    surveyModel.getDescription(),
                    surveyModel.getLimitDate(),
                    DriverMappers.toDriverDTOResponses(surveyModel.getDrivers()),
                    surveyModel.getVotes() != null
                            ? VoteMappers.toVoteDTOResponses(surveyModel.getVotes())
                            : List.of());
        } catch (NullPointerException npe) {
            throw new NullPointerException();
        }
    }

    public static List<SurveyDTOResponse> toSurveyDTOResponses(List<SurveyModel> surveyModels) {
        return surveyModels.stream().map(SurveyMappers::toSurveyDTOResponse).toList();
    }

    public static List<SurveyModel> toSurveyModels(List<SurveyDTORequest> surveyDTORequests) {
        return surveyDTORequests.stream().map(SurveyMappers::toSurveyModel).toList();
    }
}
