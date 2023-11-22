package com.uah.f1backend.end2end;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.CIRCUIT_ID;
import static com.uah.f1backend.configuration.common.TableNameConstants.CIRCUIT_TABLE;
import static com.uah.f1backend.utils.CircuitUtils.*;
import static com.uah.f1backend.utils.CountryUtils.dummyCountryModel;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import com.uah.f1backend.model.dto.circuit.DeletedCircuitDTOResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CircuitEnd2EndIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void CircuitCRUDTest() throws Exception {

        final var country = dummyCountryModel();
        country.setId(null);
        entityManager.persist(country);

        final var circuitDTORequest = dummyCircuitDTORequestOnIT(country.getId());

        final var gson = new Gson();

        // Insert circuit in db
        final var insertResponseAsString = mockMvc.perform(post("/circuits")
                        .content(gson.toJson(circuitDTORequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var insertResponseEntity = objectMapper.readValue(insertResponseAsString, CircuitDTOResponse.class);

        final var expectedCircuitDTOResponse = dummyCircuitDTOResponseOnIT(country.getId());

        // Check that response object is the one expected
        Assertions.assertEquals(expectedCircuitDTOResponse, insertResponseEntity);

        final var expectedName = circuitDTORequest.getName();

        // Get circuit from db via entityManager
        final var actualCircuitModel = (CircuitModel) entityManager
                .createQuery("from " + CIRCUIT_TABLE + " where " + CIRCUIT_ID + "=" + insertResponseEntity.getId())
                .getResultList()
                .get(0);

        // Check that the requested object has been saved in db
        Assertions.assertEquals(expectedName, actualCircuitModel.getName());

        // Check that via API the object can be retrieved too
        final var getResponseAsString = mockMvc.perform(
                        get("/circuits/" + actualCircuitModel.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var getResponseEntity = objectMapper.readValue(getResponseAsString, CircuitDTOResponse.class);

        Assertions.assertEquals(expectedCircuitDTOResponse, getResponseEntity);

        // Update circuit
        final var updatedCircuitDTORequest = dummyCircuitDTORequest2OnIT(country.getId());

        final var updateResponseAsString = mockMvc.perform(put("/circuits/" + actualCircuitModel.getId())
                        .content(gson.toJson(updatedCircuitDTORequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var updateResponseEntity = objectMapper.readValue(updateResponseAsString, CircuitDTOResponse.class);
        final var updatedExpectedCircuitDTOResponse = dummyCircuitDTOResponse2OnIT(country.getId());

        Assertions.assertEquals(updatedExpectedCircuitDTOResponse, updateResponseEntity);

        // Check that circuit has been updated in db
        final var updatedCircuitModel = (CircuitModel) entityManager
                .createQuery("from " + CIRCUIT_TABLE + " where " + CIRCUIT_ID + "=" + actualCircuitModel.getId())
                .getResultList()
                .get(0);

        final var expectedUpdatedCircuitModel = dummyCircuitModel2();

        Assertions.assertEquals(expectedUpdatedCircuitModel, updatedCircuitModel);

        // Remove circuit from db
        final var deleteResponseAsString = mockMvc.perform(
                        delete("/circuits/" + actualCircuitModel.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final var deleteResponseEntity =
                objectMapper.readValue(deleteResponseAsString, DeletedCircuitDTOResponse.class);

        // Check that response body contains the circuit name
        Assertions.assertEquals(updatedCircuitDTORequest.getName(), deleteResponseEntity.getCircuitName());

        // Check that the circuit has been removed from db
        Assertions.assertEquals(
                entityManager
                        .createQuery(
                                "from " + CIRCUIT_TABLE + " where " + CIRCUIT_ID + "=" + actualCircuitModel.getId())
                        .getResultList()
                        .size(),
                0);
    }
}
