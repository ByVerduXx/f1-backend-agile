package com.uah.f1backend.end2end;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.TEAM_ID;
import static com.uah.f1backend.configuration.common.TableNameConstants.TEAM_TABLE;
import static com.uah.f1backend.utils.TeamUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uah.f1backend.controller.TeamRestController;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class TeamEnd2EndIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TeamRestController teamRestController;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void insertAndDeleteTeam() throws Exception {

        final var teamDTORequest = dummyTeamDTORequest();

        final var gson = new Gson();

        // Insert team in db
        final var insertResponseAsString = mockMvc.perform(
                        post("/teams").content(gson.toJson(teamDTORequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var insertResponseEntity = objectMapper.readValue(insertResponseAsString, TeamDTOResponse.class);

        final var expectedTeamDTOResponse = dummyTeamDTOResponseOnIT(insertResponseEntity.getId());

        // Check that response object is the one expected
        Assertions.assertEquals(expectedTeamDTOResponse, insertResponseEntity);

        final var expectedName = teamDTORequest.getName();

        // Get team from db via entityManager
        final var actualTeamModel = (TeamModel) entityManager
                .createQuery("from " + TEAM_TABLE + " where " + TEAM_ID + "=" + insertResponseEntity.getId())
                .getResultList()
                .get(0);

        // Check that the requested object has been saved in db
        Assertions.assertEquals(expectedName, actualTeamModel.getName());

        // Check that via API the object can be retrieved too
        final var getResponseAsString = mockMvc.perform(
                        get("/teams/" + actualTeamModel.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var getResponseEntity = objectMapper.readValue(getResponseAsString, TeamDTOResponse.class);

        Assertions.assertEquals(expectedTeamDTOResponse, getResponseEntity);

        // Update team
        final var updatedTeamDTORequest = dummyTeamDTORequest2();

        final var updateResponseAsString = mockMvc.perform(put("/teams/" + actualTeamModel.getId())
                        .content(gson.toJson(updatedTeamDTORequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var updateResponseEntity = objectMapper.readValue(updateResponseAsString, TeamDTOResponse.class);
        final var updatedExpectedTeamDTOResponse = dummyTeamDTOResponseOnUpdateIT(updateResponseEntity.getId());

        Assertions.assertEquals(updatedExpectedTeamDTOResponse, updateResponseEntity);

        // Check that team has been updated in db
        final var updatedTeamModel = (TeamModel) entityManager
                .createQuery("from " + TEAM_TABLE + " where " + TEAM_ID + "=" + actualTeamModel.getId())
                .getResultList()
                .get(0);

        Assertions.assertEquals(updatedTeamDTORequest.getName(), updatedTeamModel.getName());
        Assertions.assertEquals(updatedTeamDTORequest.getLogo(), updatedTeamModel.getLogo());
        Assertions.assertEquals(updatedTeamDTORequest.getTwitter(), updatedTeamModel.getTwitter());

        // Remove team from db
        final var deleteResponseAsString = mockMvc.perform(
                        delete("/teams/" + actualTeamModel.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var deleteResponseEntity = objectMapper.readValue(deleteResponseAsString, DeletedTeamDTOResponse.class);

        // Check that response body contains the team name
        Assertions.assertEquals(updatedTeamDTORequest.getName(), deleteResponseEntity.getTeamName());

        // Check that the team has been removed from db
        Assertions.assertEquals(
                entityManager
                        .createQuery("from " + TEAM_TABLE + " where " + TEAM_ID + "=" + actualTeamModel.getId())
                        .getResultList()
                        .size(),
                0);
    }
}
