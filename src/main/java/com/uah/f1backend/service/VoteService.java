package com.uah.f1backend.service;

import static com.uah.f1backend.configuration.HttpExceptions.*;

import com.uah.f1backend.model.dto.vote.VoteDTORequest;
import com.uah.f1backend.model.dto.vote.VoteDTOResponse;
import com.uah.f1backend.model.mapper.vote.VoteMappers;
import com.uah.f1backend.repository.DriverModelRepository;
import com.uah.f1backend.repository.SurveyModelRepository;
import com.uah.f1backend.repository.VoteModelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteModelRepository voteModelRepository;
    private final SurveyModelRepository surveyModelRepository;
    private final DriverModelRepository driverModelRepository;

    public List<VoteDTOResponse> obtainAllVotes() {
        return VoteMappers.toVoteDTOResponses(voteModelRepository.findAll());
    }

    public VoteDTOResponse obtainVoteById(Integer id) {
        return VoteMappers.toVoteDTOResponse(
                voteModelRepository.findById(id).orElseThrow(VoteDoesntExistException::new));
    }

    public VoteDTOResponse insertVote(VoteDTORequest voteDTORequest) {
        try {
            final var voteModel = VoteMappers.toVoteModel(voteDTORequest);
            if (voteModelRepository.existsByVoterNameOrVoterEmailAndSurveyIdAndDriverId(
                    voteModel.getVoterName(),
                    voteModel.getVoterEmail(),
                    voteDTORequest.getSurveyId(),
                    voteDTORequest.getDriverId())) {
                throw new VoteAlreadyExistsException();
            }
            final var survey = surveyModelRepository
                    .findById(voteDTORequest.getSurveyId())
                    .orElseThrow(SurveyDoesntExistException::new);
            final var driver = driverModelRepository
                    .findById(voteDTORequest.getDriverId())
                    .orElseThrow(DriverDoesntExistException::new);

            if (!survey.getDrivers().contains(driver)) {
                throw new SurveyDoesntContainDriverException();
            }

            voteModel.setSurvey(survey);
            voteModel.setDriver(driver);
            return VoteMappers.toVoteDTOResponse(voteModelRepository.save(voteModel));
        } catch (NullPointerException npe) {
            throw new VoteNotSavedException();
        }
    }

    public VoteDTOResponse updateVote(Integer id, VoteDTORequest voteDTORequest) {
        try {
            final var voteModel = voteModelRepository.findById(id).orElseThrow(VoteDoesntExistException::new);
            final var survey = surveyModelRepository
                    .findById(voteDTORequest.getSurveyId())
                    .orElseThrow(SurveyDoesntExistException::new);
            final var driver = driverModelRepository
                    .findById(voteDTORequest.getDriverId())
                    .orElseThrow(DriverDoesntExistException::new);

            if (!survey.getDrivers().contains(driver)) {
                throw new SurveyDoesntContainDriverException();
            }

            voteModel.setVoterName(voteDTORequest.getVoterName());
            voteModel.setVoterEmail(voteDTORequest.getVoterEmail());
            voteModel.setSurvey(survey);
            voteModel.setDriver(driver);
            return VoteMappers.toVoteDTOResponse(voteModelRepository.save(voteModel));
        } catch (NullPointerException npe) {
            throw new VoteNotSavedException();
        }
    }

    public String deleteVote(Integer id) {
        try {
            final var voteModel = voteModelRepository.findById(id).orElseThrow(VoteDoesntExistException::new);
            voteModelRepository.delete(voteModel);
            return "Vote with id " + id + " has been deleted";
        } catch (NullPointerException npe) {
            throw new VoteDoesntExistException();
        }
    }
}
