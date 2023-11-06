package com.uah.f1backend.end2end;

import static com.uah.f1backend.utils.CarUtils.*;
import static com.uah.f1backend.utils.TeamUtils.dummyTeamModel;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uah.f1backend.controller.TeamRestController;
import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarEnd2EndIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TeamRestController teamRestController;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void CarCRUDTest() throws Exception {
        final var gson = new Gson();

        final var team = dummyTeamModel();
        team.setId(null);
        entityManager.persist(team);

        final var carDTORequest = dummyCarDTORequestIT(team.getId());
        final var expectedCarDTOResponse = dummyCarDTOResponseIT(team.getName());

        final var insertResponseAsString = mockMvc.perform(
                        post("/cars").content(gson.toJson(carDTORequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var insertResponseEntity = objectMapper.readValue(insertResponseAsString, CarDTOResponse.class);

        Assertions.assertEquals(expectedCarDTOResponse, insertResponseEntity);

        final var savedCar = entityManager.find(CarModel.class, insertResponseEntity.getId());

        Assertions.assertEquals(insertResponseEntity.getId(), savedCar.getId());
        Assertions.assertEquals(carDTORequest.getName(), savedCar.getName());

        final var getResponseAsString = mockMvc.perform(
                        get("/cars/" + insertResponseEntity.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var getResponseEntity = objectMapper.readValue(getResponseAsString, CarDTOResponse.class);

        Assertions.assertEquals(expectedCarDTOResponse, getResponseEntity);

        final var updatedCarDTORequest = dummyCarDTORequestIT2(team.getId());
        final var expectedUpdatedCarDTOResponse = dummyCarDTOResponseIT2(team.getName());

        final var updateResponseAsString = mockMvc.perform(put("/cars/" + insertResponseEntity.getId())
                        .content(gson.toJson(updatedCarDTORequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var updateResponseEntity = objectMapper.readValue(updateResponseAsString, CarDTOResponse.class);

        Assertions.assertEquals(expectedUpdatedCarDTOResponse, updateResponseEntity);

        final var updatedCar = entityManager.find(CarModel.class, updateResponseEntity.getId());

        Assertions.assertEquals(updateResponseEntity.getId(), updatedCar.getId());
        Assertions.assertEquals(updatedCarDTORequest.getName(), updatedCar.getName());
        Assertions.assertEquals(updatedCarDTORequest.getCode(), updatedCar.getCode());

        mockMvc.perform(delete("/cars/" + insertResponseEntity.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        final var deletedCar = entityManager.find(CarModel.class, insertResponseEntity.getId());

        Assertions.assertNull(deletedCar);
    }
}
