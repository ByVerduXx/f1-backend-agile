package com.uah.f1backend.configuration;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class HttpExceptions {
    @ResponseStatus(value = UNPROCESSABLE_ENTITY)
    public static class ResourceNotSavedException extends RuntimeException {
    }

    @ResponseStatus(code = NOT_FOUND, reason = "Team not found")
    public static class TeamDoesntExistException extends RuntimeException {
    }


    @ResponseStatus(code = NOT_FOUND, reason = "Circuit not found")
    public static class CircuitDoesntExistException extends RuntimeException {
    }

}