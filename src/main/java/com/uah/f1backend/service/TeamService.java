package com.uah.f1backend.service;

import static com.uah.f1backend.common.GenericValidations.isValidTwitter;
import static com.uah.f1backend.common.GenericValidations.isValidUrl;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.model.mapper.team.TeamMappers;
import com.uah.f1backend.repository.TeamModelRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamModelRepository teamModelRepository;

    // Retrieve all the teams in the db
    public List<TeamDTOResponse> getAllTeams() {
        return TeamMappers.toTeamListDTOResponseMapper(teamModelRepository.findAll());
    }

    // Retrieve the team matching the given name
    public TeamDTOResponse getTeamByName(String name) {
        final var team = teamModelRepository.findByName(name).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
        return TeamMappers.toTeamDTOResponseMapper(team);
    }

    // Retrieve the team matching the given id
    public TeamDTOResponse getTeamById(Integer id) {
        final var team = teamModelRepository.findById(id).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
        return TeamMappers.toTeamDTOResponseMapper(team);
    }

    // Add new team in the db
    public TeamDTOResponse insertTeam(TeamDTORequest team) {
        try {
            final var tm = TeamMappers.toTeamModelMapper(team);

            // Validate that name doesnt exist in db
            final var isUsedName = teamModelRepository.findByName(tm.getName()).isPresent();
            if (isUsedName) {
                throw new HttpExceptions.TeamNameInUseException();
            }

            validateTeamFields(tm);

            return TeamMappers.toTeamDTOResponseMapper(teamModelRepository.save(tm));
        } catch (NullPointerException e) {
            throw new HttpExceptions.TeamNotSavedException();
        }
    }

    // Remove team from db given its name
    public DeletedTeamDTOResponse deleteTeamByName(String name) {
        final var team = teamModelRepository.findByName(name).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
        teamModelRepository.delete(team);
        return new DeletedTeamDTOResponse("Team deleted", name);
    }

    // Remove team from db given its id
    public DeletedTeamDTOResponse deleteTeamById(Integer id) {
        final var team = teamModelRepository.findById(id).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
        teamModelRepository.deleteById(id);
        return new DeletedTeamDTOResponse("Team deleted", team.getName());
    }

    // Update a team existing in db
    public TeamDTOResponse updateTeamById(Integer id, TeamDTORequest team) {
        try {
            final var tm = teamModelRepository.findById(id).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
            tm.setName(team.getName());
            tm.setLogo(team.getLogo());
            tm.setTwitter(team.getTwitter());

            // Validate that name doesn't exist in other db team
            final var teamsWithSameName = teamModelRepository.findByName(tm.getName());
            final var isUsedName = teamsWithSameName.isPresent();
            // Check if the one with the same name is other team
            // If it's the current one let it pass the validation because the name isn't being modified but other fields
            if (isUsedName && !Objects.equals(teamsWithSameName.get().getId(), tm.getId())) {
                throw new HttpExceptions.TeamNameInUseException();
            }

            // Validate that fields matches the correct format
            validateTeamFields(tm);

            teamModelRepository.save(tm);
            return TeamMappers.toTeamDTOResponseMapper(tm);
        } catch (NullPointerException e) {
            throw new HttpExceptions.TeamNotSavedException();
        }
    }

    // Team field format validations
    private static void validateTeamFields(TeamModel tm) {
        // Validate that name size is at least 4 characters()
        if (tm.getName().length() < 4) {
            throw new HttpExceptions.TeamNameLengthException();
        }

        // Validate that logo is a valid url
        if (!isValidUrl(tm.getLogo())) {
            throw new HttpExceptions.InvalidUrlFormatException();
        }

        // Validate that twitter is a valid Twitter account (can be empty)
        if (tm.getTwitter() != null && !isValidTwitter(tm.getTwitter())) {
            throw new HttpExceptions.InvalidTwitterFormatException();
        }
    }
}
