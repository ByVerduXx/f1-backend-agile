package com.uah.f1backend.end2end;


import com.uah.f1backend.controller.CircuitRestController;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.CircuitDTORequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CircuitEnd2EndIT {

    @Autowired
    CircuitRestController circuitRestController;
    @Autowired
    EntityManager entityManager;

    @Test
    void insertAndDeleteCircuit() throws Exception {
        final var circuitDTORequest = new CircuitDTORequest("name", "city", 1, "image", 1, 1, 1, 1, 1);;

        // Insert circuit in db
        final var circuitResponseEntity = circuitRestController.insertCircuit(circuitDTORequest);

        // Check that response object is the one expected
        Assertions.assertEquals(circuitDTORequest.getName(), circuitResponseEntity.getBody().getName());
        Assertions.assertEquals(circuitDTORequest.getCity(), circuitResponseEntity.getBody().getCity());
        Assertions.assertEquals(circuitDTORequest.getId_country(), circuitResponseEntity.getBody().getId_country());
        Assertions.assertEquals(circuitDTORequest.getImage(), circuitResponseEntity.getBody().getImage());
        Assertions.assertEquals(circuitDTORequest.getLaps(), circuitResponseEntity.getBody().getLaps());
        Assertions.assertEquals(circuitDTORequest.getLength(), circuitResponseEntity.getBody().getLength());
        Assertions.assertEquals(circuitDTORequest.getSlow_turns(), circuitResponseEntity.getBody().getSlow_turns());
        Assertions.assertEquals(circuitDTORequest.getMedium_turns(), circuitResponseEntity.getBody().getMedium_turns());
        Assertions.assertEquals(circuitDTORequest.getFast_turns(), circuitResponseEntity.getBody().getFast_turns());



        final var expectedName = circuitDTORequest.getName();

        // Get circuit from db via entityManager
        final var actualCircuitModel = (CircuitModel) entityManager
                .createQuery("from circuit where id=" + circuitResponseEntity.getBody().getId())
                .getResultList().get(0);

        // Check that the requested object has been saved in db
        Assertions.assertEquals(expectedName, actualCircuitModel.getName());

        // Check that via restController the object can be retrieved too
        final var getResponseEntity = circuitRestController.getCircuits(actualCircuitModel.getId());
        Assertions.assertEquals(circuitDTORequest.getName(), getResponseEntity.getBody().getName());
        Assertions.assertEquals(circuitDTORequest.getCity(), getResponseEntity.getBody().getCity());
        Assertions.assertEquals(circuitDTORequest.getId_country(), getResponseEntity.getBody().getId_country());
        Assertions.assertEquals(circuitDTORequest.getImage(), getResponseEntity.getBody().getImage());
        Assertions.assertEquals(circuitDTORequest.getLaps(), getResponseEntity.getBody().getLaps());
        Assertions.assertEquals(circuitDTORequest.getLength(), getResponseEntity.getBody().getLength());
        Assertions.assertEquals(circuitDTORequest.getSlow_turns(), getResponseEntity.getBody().getSlow_turns());
        Assertions.assertEquals(circuitDTORequest.getMedium_turns(), getResponseEntity.getBody().getMedium_turns());
        Assertions.assertEquals(circuitDTORequest.getFast_turns(), getResponseEntity.getBody().getFast_turns());


        // Update circuit
        final var updatedCircuitDTORequest = new CircuitDTORequest("name", "city", 1, "image", 1, 1, 1, 1, 1);;
        final var getUpdateResponseEntity = circuitRestController.updateCircuitById(actualCircuitModel.getId(), updatedCircuitDTORequest);

        Assertions.assertEquals(updatedCircuitDTORequest.getName(), getUpdateResponseEntity.getBody().getName());
        Assertions.assertEquals(updatedCircuitDTORequest.getCity(), getUpdateResponseEntity.getBody().getCity());
        Assertions.assertEquals(updatedCircuitDTORequest.getId_country(), getUpdateResponseEntity.getBody().getId_country());
        Assertions.assertEquals(updatedCircuitDTORequest.getImage(), getUpdateResponseEntity.getBody().getImage());
        Assertions.assertEquals(updatedCircuitDTORequest.getLaps(), getUpdateResponseEntity.getBody().getLaps());
        Assertions.assertEquals(updatedCircuitDTORequest.getLength(), getUpdateResponseEntity.getBody().getLength());
        Assertions.assertEquals(updatedCircuitDTORequest.getSlow_turns(), getUpdateResponseEntity.getBody().getSlow_turns());
        Assertions.assertEquals(updatedCircuitDTORequest.getMedium_turns(), getUpdateResponseEntity.getBody().getMedium_turns());
        Assertions.assertEquals(updatedCircuitDTORequest.getFast_turns(), getUpdateResponseEntity.getBody().getFast_turns());



        // Check that circuit has been updated in db
        final var updatedCircuitModel = (CircuitModel) entityManager
                .createQuery("from circuit where id=" + actualCircuitModel.getId())
                .getResultList().get(0);

        Assertions.assertEquals(updatedCircuitDTORequest.getName(), updatedCircuitModel.getName());
        Assertions.assertEquals(updatedCircuitDTORequest.getCity(), updatedCircuitModel.getCity());
        Assertions.assertEquals(updatedCircuitDTORequest.getId_country(), updatedCircuitModel.getId_country());
        Assertions.assertEquals(updatedCircuitDTORequest.getImage(), updatedCircuitModel.getImage());
        Assertions.assertEquals(updatedCircuitDTORequest.getLaps(), updatedCircuitModel.getLaps());
        Assertions.assertEquals(updatedCircuitDTORequest.getLength(), updatedCircuitModel.getLength());
        Assertions.assertEquals(updatedCircuitDTORequest.getSlow_turns(), updatedCircuitModel.getSlow_turns());
        Assertions.assertEquals(updatedCircuitDTORequest.getMedium_turns(), updatedCircuitModel.getMedium_turns());
        Assertions.assertEquals(updatedCircuitDTORequest.getFast_turns(), updatedCircuitModel.getFast_turns());



        // Remove circuit from db
        final var deletedCircuitResponseEntity = circuitRestController.deleteCircuitById(actualCircuitModel.getId());

        // Check that response code is 200
        Assertions.assertEquals(200, deletedCircuitResponseEntity.getStatusCode().value());

        // Check that response body contains the circuit name
        Assertions.assertEquals(updatedCircuitDTORequest.getName(), deletedCircuitResponseEntity.getBody().getCircuitName());

        // Check that the circuit has been removed from db
        Assertions.assertEquals(entityManager
                .createQuery("from circuit where id=" + actualCircuitModel.getId())
                .getResultList().size(), 0);
    }
}
