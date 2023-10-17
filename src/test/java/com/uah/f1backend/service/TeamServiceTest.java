package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpStatus;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.model.mapper.team.TeamMappers;
import com.uah.f1backend.repository.TeamModelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

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
    void getAllTeamsEmptyTest(){
        final var teamList = new ArrayList<TeamModel>();
        Mockito.doReturn(teamList).when(teamModelRepository).findAll();
        final var actualResultList = teamService.getAllTeams();
        Assertions.assertEquals(0, actualResultList.size());
    }

    @Test
    void getAllTeamsTest(){
        final var teamList = new ArrayList<TeamModel>();
        
        for (int i = 0; i < 4; i++){
            final var tm = new TeamModel();
            tm.setId(i);
            tm.setName(Integer.toString(i));
            tm.setLogo(Integer.toString(i));
            tm.setTwitter(Integer.toString(i));
            teamList.add(tm);
        }
        Mockito.doReturn(teamList).when(teamModelRepository).findAll();
        final var actualResult = teamService.getAllTeams();

        for (int i = 0; i < 4; i++) {
            var team = actualResult.get(i);
            Assertions.assertEquals(i, team.getId());
            Assertions.assertEquals(Integer.toString(i), team.getName());
            Assertions.assertEquals(Integer.toString(i), team.getLogo());
            Assertions.assertEquals(Integer.toString(i), team.getTwitter());
        }
    }

    @Test
    void getTeamByNameTest(){
        final var team = new TeamModel();
        team.setId(1);
        team.setName("name");
        team.setLogo("logo");
        team.setTwitter("twitter");
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findByName("name");

        final var actualResult = teamService.getTeamByName("name");
        final var expectedResult = new TeamDTOResponse(1, "name", "logo", "twitter");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTeamByIdTest(){
        final var team = new TeamModel();
        team.setId(1);
        team.setName("name");
        team.setLogo("logo");
        team.setTwitter("twitter");
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findById(1L);

        final var actualResult = teamService.getTeamById(1);
        final var expectedResult = new TeamDTOResponse(1, "name", "logo", "twitter");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTeamByNameNotFoundTest(){
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findByName("name");
        Assertions.assertThrows(HttpStatus.TeamDoesntExistException.class, () -> {
            teamService.getTeamByName("name");
        });
    }

    @Test
    void getTeamByIdNotFoundTest(){
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findById(1L);
        Assertions.assertThrows(HttpStatus.TeamDoesntExistException.class, () -> {
            teamService.getTeamById(1);
        });
    }

    @Test
    void insertTeamTest(){
        final var teamToInsert = new TeamDTORequest( "name", "logo", "twitter");
        final var expectedResult = new TeamDTOResponse(1, "name", "logo", "twitter");
        final var team = TeamMappers.toTeamModelMapper(expectedResult);

        Mockito.doReturn(team).when(teamModelRepository).save(team);

        final var actualRestult = teamService.insertTeam(teamToInsert);
        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void insertTeamObjectNullTest(){
        Assertions.assertThrows(HttpStatus.ResourceNotSavedException.class, () -> {
            teamService.insertTeam(null);
        });
    }

    @Test
    void deleteTeamByNameTest(){
        final var teamName = "name";
        final var team = new TeamModel();
        team.setId(1);
        team.setName(teamName);
        team.setLogo("logo");
        team.setTwitter("twitter");
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findByName(teamName);
        Mockito.doNothing().when(teamModelRepository).delete(team);

        final var expectedResult = new DeletedTeamDTOResponse("Team deleted", teamName);
        final var actualRestult = teamService.deleteTeamByName(teamName);

        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void deleteTeamByIdTest(){
        final var teamId = 1;
        final var team = new TeamModel();
        team.setId(teamId);
        team.setName("name");
        team.setLogo("logo");
        team.setTwitter("twitter");
        Mockito.doReturn(Optional.of(team)).when(teamModelRepository).findById((long) teamId);
        Mockito.doNothing().when(teamModelRepository).delete(team);

        final var expectedResult = new DeletedTeamDTOResponse("Team deleted", team.getName());
        final var actualRestult = teamService.deleteTeamById(teamId);

        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void deleteTeamByNameNotFoundTest(){
        final var teamName = "name";
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findByName(teamName);
        Assertions.assertThrows(HttpStatus.TeamDoesntExistException.class, () -> {
            teamService.deleteTeamByName(teamName);
        });
    }

    @Test
    void deleteTeamByIdNotFoundTest(){
        final var teamId = 1;
        Mockito.doReturn(Optional.empty()).when(teamModelRepository).findById((long) teamId);
        Assertions.assertThrows(HttpStatus.TeamDoesntExistException.class, () -> {
            teamService.deleteTeamById(teamId);
        });
    }
}
