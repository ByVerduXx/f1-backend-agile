package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpStatus;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTOResponseRequest;
import com.uah.f1backend.model.mapper.team.TeamMappers;
import com.uah.f1backend.repository.TeamModelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
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
        final var expectedResultList = new ArrayList<TeamDTOResponseRequest>();
        assert teamService.getAllTeams().equals(expectedResultList);
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
            assert actualResult.get(i).getName().equals(Integer.toString(i));
            assert actualResult.get(i).getLogo().equals(Integer.toString(i));
            assert actualResult.get(i).getTwitter().equals(Integer.toString(i));
        }
    }

    @Test
    void getTeamByNameTest(){
        final var team = new TeamModel();
        team.setId(1);
        team.setName("name");
        team.setLogo("logo");
        team.setTwitter("twitter");
        Mockito.doReturn(team).when(teamModelRepository).findByName("name");

        final var actualResult = teamService.getTeamByName("name");
        final var expectedResult = new TeamDTOResponseRequest("name", "logo", "twitter");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTeamByNameNotFoundTest(){
        Mockito.doReturn(null).when(teamModelRepository).findByName("name");
        Assertions.assertThrows(HttpStatus.TeamDoesntExistException.class, () -> {
            teamService.getTeamByName("name");
        });
    }

    @Test
    void insertTeamTest(){
        final var expectedResult = new TeamDTOResponseRequest("name", "logo", "twitter");
        final var team = TeamMappers.teamModelMapper(expectedResult);
        Mockito.doReturn(team).when(teamModelRepository).save(team);
        final var actualRestult = teamService.insertTeam(expectedResult);
        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void insertTeamObjectNullTest(){
        Assertions.assertThrows(HttpStatus.ResourceNotSavedException.class, () -> {
            teamService.insertTeam(null);
        });
    }

    @Test
    void deleteTeamTest(){
        final var teamName = "name";
        final var team = new TeamModel();
        team.setId(1);
        team.setName(teamName);
        team.setLogo("logo");
        team.setTwitter("twitter");
        Mockito.doReturn(team).when(teamModelRepository).findByName(teamName);
        Mockito.doNothing().when(teamModelRepository).delete(team);

        final var expectedResult = new DeletedTeamDTOResponse("Team deleted", teamName);
        final var actualRestult = teamService.deleteTeam(teamName);

        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void deleteTeamNotFoundTest(){
        final var teamName = "name";
        Mockito.doReturn(null).when(teamModelRepository).findByName(teamName);
        Assertions.assertThrows(HttpStatus.TeamDoesntExistException.class, () -> {
            teamService.deleteTeam(teamName);
        });
    }
}
