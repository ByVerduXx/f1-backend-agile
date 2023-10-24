package com.uah.f1backend.end2end;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.*;
import static com.uah.f1backend.utils.DriverUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uah.f1backend.controller.DriverRestController;
import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.dto.driver.DeletedDriverDTOResponse;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DriverEnd2EndIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DriverRestController driverRestController;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void insertAndDeleteDriver() throws Exception {
        final var driverDTORequest = dummyDriverDTORequest();
        final var expectedDriverDTOResponse = dummyDriverDTOResponseIT();

        final var gson = new Gson();

        final var insertResponseAsString = mockMvc.perform(
                        post("/drivers").content(gson.toJson(driverDTORequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var insertResponseEntity = objectMapper.readValue(insertResponseAsString, DriverDTOResponse.class);

        Assertions.assertEquals(expectedDriverDTOResponse, insertResponseEntity);

        final var expectedDorsal = driverDTORequest.getDorsal();

        // Check that the driver has been inserted in the database
        final var driverModel = (DriverModel) entityManager
                .createQuery("from " + DRIVER_TABLE + " where " + TEAM_ID + " = " + insertResponseEntity.getId())
                .getResultList()
                .get(0);

        Assertions.assertEquals(expectedDorsal, driverModel.getDorsal());

        // Check that the driver has been inserted in the database via API
        final var obtainDriverResponseAsString = mockMvc.perform(
                        get("/drivers/" + insertResponseEntity.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var actualResponseEntity = objectMapper.readValue(obtainDriverResponseAsString, DriverDTOResponse.class);

        Assertions.assertEquals(expectedDriverDTOResponse, actualResponseEntity);

        // Update the driver
        final var updatedDriverDTORequest = dummy2DriverDTORequest();
        final var updatedExpectedDriverDTOResponse = dummy2DriverDTOResponseIT();

        final var updateResponseAsString = mockMvc.perform(put("/drivers/" + insertResponseEntity.getId())
                        .content(gson.toJson(updatedDriverDTORequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var actualUpdateResponseEntity = objectMapper.readValue(updateResponseAsString, DriverDTOResponse.class);

        Assertions.assertEquals(updatedExpectedDriverDTOResponse, actualUpdateResponseEntity);

        // Check that the driver has been updated in the database
        final var updatedDriverModel = (DriverModel) entityManager
                .createQuery("from " + DRIVER_TABLE + " where " + TEAM_ID + " = " + insertResponseEntity.getId())
                .getResultList()
                .get(0);

        Assertions.assertEquals(updatedDriverDTORequest.getName(), updatedDriverModel.getName());
        Assertions.assertEquals(updatedDriverDTORequest.getLastName(), updatedDriverModel.getLastName());
        Assertions.assertEquals(updatedDriverDTORequest.getInitial(), updatedDriverModel.getInitial());
        Assertions.assertEquals(updatedDriverDTORequest.getPhoto(), updatedDriverModel.getPhoto());

        // Delete the driver from the database
        final var deleteResponseAsString = mockMvc.perform(
                        delete("/drivers/" + insertResponseEntity.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var deleteResponseEntity = objectMapper.readValue(deleteResponseAsString, DeletedDriverDTOResponse.class);

        Assertions.assertEquals(updatedDriverModel.getId(), deleteResponseEntity.getId());

        // Check that driver has been removed
        Assertions.assertEquals(
                entityManager
                        .createQuery("from " + DRIVER_TABLE)
                        .getResultList()
                        .size(),
                0);
    }
}
