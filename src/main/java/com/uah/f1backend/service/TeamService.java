package com.uah.f1backend.service;

import static com.uah.f1backend.common.GenericValidations.isValidTwitter;
import static com.uah.f1backend.common.GenericValidations.isValidUrl;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.UserModel;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDetailDTOResponse;
import com.uah.f1backend.model.mapper.team.TeamMappers;
import com.uah.f1backend.repository.TeamModelRepository;
import java.util.List;
import java.util.Objects;

import com.uah.f1backend.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamModelRepository teamModelRepository;
    private final UserModelRepository userModelRepository;
    private final SecurityService securityService;

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

    // Retrieve the team with drivers and car details matching the given id
    public TeamDetailDTOResponse getTeamDetailById(Integer id) {
        final var team = teamModelRepository.findById(id).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
        return TeamMappers.toTeamDetailDTOResponseMapper(team);
    }

    // Add new team in the db
    @Transactional
    public TeamDTOResponse insertTeam(TeamDTORequest team) {

        var user = securityService.getUserAuthenticated();
        if (user.getTeam() != null) {
            throw new HttpExceptions.TeamNotSavedException();
        }

        try {

            var tm = TeamMappers.toTeamModelMapper(team);

            // Validate that name doesnt exist in db
            final var isUsedName = teamModelRepository.findByName(tm.getName()).isPresent();
            if (isUsedName) {
                throw new HttpExceptions.TeamNameInUseException();
            }

            validateTeamFields(tm);

            tm = teamModelRepository.save(tm);
            user.setTeam(tm);
            userModelRepository.save(user);
            return TeamMappers.toTeamDTOResponseMapper(tm);
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
    @Transactional
    public TeamDTOResponse updateTeamById(Integer id, TeamDTORequest team) {

        var user = securityService.getUserAuthenticated();

        //team to modify is not the same as the user's team or the user is not an Admin
        if (user.getRole().getRoleName().equals("ROLE_MANAGER") && !user.getTeam().getId().equals(id)) {
            throw new HttpExceptions.TeamNotSavedException();
        }

        try {
            var tm = teamModelRepository.findById(id).orElseThrow(HttpExceptions.TeamDoesntExistException::new);
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
            for (UserModel u : tm.getUsers()) {
                u.setTeam(tm);
                userModelRepository.save(u);
            }
            return TeamMappers.toTeamDTOResponseMapper(tm);
        } catch (NullPointerException e) {
            throw new HttpExceptions.TeamNotSavedException();
        }
    }

    public TeamDetailDTOResponse getTeamByUserAuthenticated() {
        var user = securityService.getUserAuthenticated();
        return TeamMappers.toTeamDetailDTOResponseMapper(user.getTeam());
    }

    public TeamDetailDTOResponse addManagerToTeam(Integer id) {
        var user = securityService.getUserAuthenticated();
        if (user.getTeam() == null) {
            throw new HttpExceptions.TeamDoesntExistException();
        }
        var team = user.getTeam();
        var manager = userModelRepository.findById(id).orElseThrow(HttpExceptions.UserDoesntExist::new);
        if (manager.getRole().getRoleName().equals("ROLE_MANAGER")) {
            manager.setTeam(team);
            userModelRepository.save(manager);
        } else {
            throw new HttpExceptions.UserMustBeManagerException();
        }
        return TeamMappers.toTeamDetailDTOResponseMapper(team);
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
