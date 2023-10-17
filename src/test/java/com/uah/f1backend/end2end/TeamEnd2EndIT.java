package com.uah.f1backend.end2end;

import com.uah.f1backend.controller.TeamRestController;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TeamEnd2EndIT {

    @Autowired
    TeamRestController teamRestController;
    @Autowired
    EntityManager entityManager;

    @Test
    void insertAndDeleteTeam() throws Exception {
        final var teamDTORequest = new TeamDTORequest( "name", "logo", "twitter");

        // Insert team in db
        final var teamResponseEntity = teamRestController.insertTeam(teamDTORequest);

        // Check that response object is the one expected
        Assertions.assertEquals(teamDTORequest.getName(), teamResponseEntity.getBody().getName());
        Assertions.assertEquals(teamDTORequest.getLogo(), teamResponseEntity.getBody().getLogo());
        Assertions.assertEquals(teamDTORequest.getTwitter(), teamResponseEntity.getBody().getTwitter());

        final var expectedName = teamDTORequest.getName();

        // Get team from db via entityManager
        final var actualTeamModel = (TeamModel) entityManager
                .createQuery("from team where id=" + teamResponseEntity.getBody().getId())
                .getResultList().get(0);

        // Check that the requested object has been saved in db
        Assertions.assertEquals(expectedName, actualTeamModel.getName());

        // Check that via restController the object can be retrieved too
        final var getResponseEntity = teamRestController.getTeams(actualTeamModel.getId());
        Assertions.assertEquals(teamDTORequest.getName(), getResponseEntity.getBody().getName());
        Assertions.assertEquals(teamDTORequest.getLogo(), getResponseEntity.getBody().getLogo());
        Assertions.assertEquals(teamDTORequest.getTwitter(), getResponseEntity.getBody().getTwitter());

        // Update team
        final var updatedTeamDTORequest = new TeamDTORequest("name2", "logo2", "twitter2");
        final var getUpdateResponseEntity = teamRestController.updateTeamById(actualTeamModel.getId(), updatedTeamDTORequest);

        Assertions.assertEquals(updatedTeamDTORequest.getName(), getUpdateResponseEntity.getBody().getName());
        Assertions.assertEquals(updatedTeamDTORequest.getLogo(), getUpdateResponseEntity.getBody().getLogo());
        Assertions.assertEquals(updatedTeamDTORequest.getTwitter(), getUpdateResponseEntity.getBody().getTwitter());

        // Check that team has been updated in db
        final var updatedTeamModel = (TeamModel) entityManager
                .createQuery("from team where id=" + actualTeamModel.getId())
                .getResultList().get(0);

        Assertions.assertEquals(updatedTeamDTORequest.getName(), updatedTeamModel.getName());
        Assertions.assertEquals(updatedTeamDTORequest.getLogo(), updatedTeamModel.getLogo());
        Assertions.assertEquals(updatedTeamDTORequest.getTwitter(), updatedTeamModel.getTwitter());

        // Remove team from db
        final var deletedTeamResponseEntity = teamRestController.deleteTeamById(actualTeamModel.getId());

        // Check that response code is 200
        Assertions.assertEquals(200, deletedTeamResponseEntity.getStatusCode().value());

        // Check that response body contains the team name
        Assertions.assertEquals(updatedTeamDTORequest.getName(), deletedTeamResponseEntity.getBody().getTeamName());

        // Check that the team has been removed from db
        Assertions.assertEquals(entityManager
                .createQuery("from team where id=" + actualTeamModel.getId())
                .getResultList().size(), 0);
    }
}
