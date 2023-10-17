package com.uah.f1backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class HttpStatus {
    @ResponseStatus(value = NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
    }
    @ResponseStatus(value = UNPROCESSABLE_ENTITY)
    public static class ResourceNotSavedException extends RuntimeException {
    }
}
