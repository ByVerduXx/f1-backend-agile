package com.uah.f1backend.configuration;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class HttpExceptions {

    // Common Exceptions
    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Invalid url format")
    public static class InvalidUrlFormatException extends RuntimeException {
    }

    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Invalid twitter format")
    public static class InvalidTwitterFormatException extends RuntimeException {
    }

    // Team Exceptions
    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Needed fields: [name, logo, twitter (optional)]")
    public static class TeamNotSavedException extends RuntimeException {
    }

    @ResponseStatus(code = NOT_FOUND, reason = "Team not found")
    public static class TeamDoesntExistException extends RuntimeException {
    }

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Team name already exists")
    public static class TeamNameInUseException extends RuntimeException {
    }

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Team name has to be at least 4 characters")
    public static class TeamNameLengthException extends RuntimeException {
    }

}
