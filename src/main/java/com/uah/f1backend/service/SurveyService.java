package com.uah.f1backend.service;

import static com.uah.f1backend.configuration.HttpExceptions.*;

import com.uah.f1backend.model.dto.survey.SurveyDTORequest;
import com.uah.f1backend.model.dto.survey.SurveyDTOResponse;
import com.uah.f1backend.model.mapper.survey.SurveyMappers;
import com.uah.f1backend.repository.DriverModelRepository;
import com.uah.f1backend.repository.SurveyModelRepository;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyModelRepository surveyModelRepository;
    private final DriverModelRepository driverModelRepository;

    public List<SurveyDTOResponse> obtainAllActiveSurveys() {
        return SurveyMappers.toSurveyDTOResponses(surveyModelRepository.findAllByLimitDateAfter(new Date()));
    }

    public List<SurveyDTOResponse> obtainAllFinishedSurveys() {
        return SurveyMappers.toSurveyDTOResponses(surveyModelRepository.findAllByLimitDateBefore(new Date()));
    }

    public SurveyDTOResponse obtainSurveyById(Integer id) {
        return SurveyMappers.toSurveyDTOResponse(
                surveyModelRepository.findById(id).orElseThrow(SurveyDoesntExistException::new));
    }

    public SurveyDTOResponse insertSurvey(SurveyDTORequest surveyDTORequest) {
        try {
            validateSurveyFields(surveyDTORequest);
            final var surveyModel = SurveyMappers.toSurveyModel(surveyDTORequest);
            final var drivers = driverModelRepository.findAllById(surveyDTORequest.getDriverIds());

            if (drivers.size() != surveyDTORequest.getDriverIds().size()) {
                throw new DriverDoesntExistException();
            }
            surveyModel.setDrivers(drivers);
            return SurveyMappers.toSurveyDTOResponse(surveyModelRepository.save(surveyModel));
        } catch (NullPointerException npe) {
            throw new SurveyNotSavedException();
        }
    }

    public SurveyDTOResponse updateSurvey(Integer id, SurveyDTORequest surveyDTORequest) {
        try {
            validateSurveyFields(surveyDTORequest);
            final var surveyModel = surveyModelRepository.findById(id).orElseThrow(SurveyDoesntExistException::new);

            final var drivers = driverModelRepository.findAllById(surveyDTORequest.getDriverIds());

            if (drivers.size() != surveyDTORequest.getDriverIds().size()) {
                throw new DriverDoesntExistException();
            }

            surveyModel.setDrivers(drivers);
            surveyModel.setPermalink(surveyDTORequest.getPermalink());
            surveyModel.setTitle(surveyDTORequest.getTitle());
            surveyModel.setDescription(surveyDTORequest.getDescription());
            surveyModel.setLimitDate(surveyDTORequest.getLimitDate());

            return SurveyMappers.toSurveyDTOResponse(surveyModelRepository.save(surveyModel));
        } catch (NullPointerException e) {
            throw new SurveyNotSavedException();
        }
    }

    public String deleteSurvey(Integer id) {
        final var surveyModel = surveyModelRepository.findById(id).orElseThrow(SurveyDoesntExistException::new);
        surveyModelRepository.delete(surveyModel);
        return "Survey with id " + id + " and title " + surveyModel.getTitle() + " has been deleted";
    }

    public void validateSurveyFields(SurveyDTORequest surveyDTORequest) {
        if (surveyDTORequest.getDriverIds() == null
                || surveyDTORequest.getDriverIds().isEmpty()) {
            throw new SurveyDriverIdsRequiredException();
        }
        if (hasRepeatedDriverIds(surveyDTORequest.getDriverIds())) {
            throw new SurveyDriverIdsRepeatedException();
        }
        /*        if (!isValidUrl(surveyDTORequest.getPermalink())) {
        	throw new InvalidUrlFormatException();
        }*/
    }

    public boolean hasRepeatedDriverIds(List<Integer> driverIds) {
        return driverIds.stream().distinct().count() != driverIds.size();
    }
}
