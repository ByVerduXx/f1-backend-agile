package com.uah.f1backend.model.mapper.vote;

import com.uah.f1backend.model.VoteModel;
import com.uah.f1backend.model.dto.vote.VoteDTORequest;
import com.uah.f1backend.model.dto.vote.VoteDTOResponse;
import com.uah.f1backend.model.mapper.driver.DriverMappers;
import java.util.List;

public class VoteMappers {
    public static VoteModel toVoteModel(VoteDTORequest dto) {
        try {
            final var voteModel = new VoteModel();
            voteModel.setVoterName(dto.getVoterName());
            voteModel.setVoterEmail(dto.getVoterEmail());
            return voteModel;
        } catch (NullPointerException npe) {
            throw new NullPointerException();
        }
    }

    public static VoteDTOResponse toVoteDTOResponse(VoteModel model) {
        try {
            return new VoteDTOResponse(
                    model.getId(),
                    model.getVoterName(),
                    model.getVoterEmail(),
                    DriverMappers.toDriverDTOResponse(model.getDriver()));
        } catch (NullPointerException npe) {
            throw new NullPointerException();
        }
    }

    public static List<VoteDTOResponse> toVoteDTOResponses(List<VoteModel> models) {
        return models.stream().map(VoteMappers::toVoteDTOResponse).toList();
    }

    public static List<VoteModel> toVoteModels(List<VoteDTORequest> dtos) {
        return dtos.stream().map(VoteMappers::toVoteModel).toList();
    }
}
