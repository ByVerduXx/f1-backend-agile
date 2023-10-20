package com.uah.f1backend.service;

import static com.uah.f1backend.utils.TeamUtils.*;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.model.mapper.team.TeamMappers;
import com.uah.f1backend.repository.TeamModelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TeamServiceTest {
    @Mock
    TeamModelRepository teamModelRepository;

    AutoCloseable closeable;
    TeamService teamService;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        teamService = new TeamService(teamModelRepository);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getAllTeamsEmptyTest() {
        final var teamList = new ArrayList<TeamModel>();
        Mockito.doReturn(teamList).when(teamModelRepository).findAll();
        final var actualResultList = teamService.getAllTeams();
        Assertions.assertEquals(0, actualResultList.size());
    }

    @Test
    void getAllTeamsTest() {
        final var teamList = dummyListTeamModel();
        Mockito.doReturn(teamList).when(teamModelRepository).findAll();
        final var expectedResult = dummyListTeamDTOResponse();
        final var actualResult = teamService.getAllTeams();

        for (var i = 0; i < actualResult.size(); i++) {
            Assertions.assertEquals(expectedResult.get(i), actualResult.get(i));
        }
    }

    @Test
    void getTeamByNameTest() {
        final var team = dummyTeamModel();
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findByName(team.getName());

        final var actualResult = teamService.getTeamByName(team.getName());
        final var expectedResult = dummyTeamDTOResponse();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTeamByIdTest() {
        final var team = dummyTeamModel();
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findById(Long.valueOf(team.getId()));

        final var actualResult = teamService.getTeamById(1);
        final var expectedResult = dummyTeamDTOResponse();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTeamByNameNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findByName("name");
        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            teamService.getTeamByName("name");
        });
    }

    @Test
    void getTeamByIdNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findById(1L);
        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            teamService.getTeamById(1);
        });
    }

    @Test
    void insertTeamTest() {
        final var teamToInsert = dummyTeamDTORequest();
        final var expectedResult = dummyTeamDTOResponse();
        final var team = TeamMappers.toTeamModelMapper(expectedResult);

        Mockito.doReturn(team).when(teamModelRepository).save(team);

        final var actualRestult = teamService.insertTeam(teamToInsert);
        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void insertTeamObjectNullTest() {
        Assertions.assertThrows(HttpExceptions.TeamNotSavedException.class, () -> {
            teamService.insertTeam(null);
        });
    }

    @Test
    void deleteTeamByNameTest() {
        final var team = dummyTeamModel();
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findByName(team.getName());
        Mockito.doNothing().when(teamModelRepository).delete(team);

        final var expectedResult = new DeletedTeamDTOResponse("Team deleted", team.getName());
        final var actualRestult = teamService.deleteTeamByName(team.getName());

        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void deleteTeamByIdTest() {
        final var team = dummyTeamModel();
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findById((long) team.getId());
        Mockito.doNothing().when(teamModelRepository).delete(team);

        final var expectedResult = new DeletedTeamDTOResponse("Team deleted", team.getName());
        final var actualRestult = teamService.deleteTeamById(team.getId());

        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void deleteTeamByNameNotFoundTest() {
        final var teamName = "name";
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findByName(teamName);
        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            teamService.deleteTeamByName(teamName);
        });
    }

    @Test
    void updateTeamByIdTest() {
        final var team = dummyTeamModel();

        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findById((long) team.getId());
        Mockito.doReturn(team).when(teamModelRepository).save(team);

        final var teamRequest = new TeamDTORequest(team.getName(), team.getLogo(), team.getTwitter());
        final var expectedResult = new TeamDTOResponse(team.getId(), team.getName(), team.getLogo(), team.getTwitter());
        final var actualResult = teamService.updateTeamById(team.getId(), teamRequest);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteTeamByIdNotFoundTest() {
        final var teamId = 1;
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findById((long) teamId);
        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            teamService.deleteTeamById(teamId);
        });
    }

    @Test
    void updateTeamByIdNotFoundTest() {
        final var unknownTeamId = 1;
        final var team = dummyTeamDTORequest();
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findById((long) unknownTeamId);
        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            teamService.updateTeamById(unknownTeamId, team);
        });
    }
}
