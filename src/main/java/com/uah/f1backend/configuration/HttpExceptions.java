package com.uah.f1backend.configuration;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpExceptions {

    // Common Exceptions
    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Invalid url format")
    public static class InvalidUrlFormatException extends RuntimeException {}

    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Invalid twitter format")
    public static class InvalidTwitterFormatException extends RuntimeException {}

    // Team Exceptions
    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Needed fields: [name, logo, twitter (optional)]")
    public static class TeamNotSavedException extends RuntimeException {}

    @ResponseStatus(code = NOT_FOUND, reason = "Team not found")
    public static class TeamDoesntExistException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Team name already exists")
    public static class TeamNameInUseException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Team name has to be at least 4 characters")
    public static class TeamNameLengthException extends RuntimeException {}

    // Driver Exceptions
    @ResponseStatus(code = NOT_FOUND, reason = "Driver not found")
    public static class DriverDoesntExistException extends RuntimeException {}

    @ResponseStatus(
            value = UNPROCESSABLE_ENTITY,
            reason = "Needed fields: [name, lastName, initial, dorsal, photo, twitter (optional), idCountry]")
    public static class DriverNotSavedException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Driver dorsal already exists")
    public static class DriverDorsalInUseException extends RuntimeException {}

    // Country Exceptions

    @ResponseStatus(code = NOT_FOUND, reason = "Country not found")
    public static class CountryDoesntExistException extends RuntimeException {}

    // Car Exceptions
    @ResponseStatus(code = NOT_FOUND, reason = "Car not found")
    public static class CarDoesntExistException extends RuntimeException {}

    @ResponseStatus(
            code = UNPROCESSABLE_ENTITY,
            reason =
                    "Car not saved. Maybe some needed fields are missing: [name, code, ersSlow, ersMedium, ersFast, consumption]")
    public static class CarNotSavedException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Car name has to be at least 1 character")
    public static class CarNameNotValidException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Car name already exists")
    public static class CarNameInUseException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Car code already exists")
    public static class CarCodeInUseException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Car code has to be at least 3 characters")
    public static class CarCodeNotValidException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Car ERS value has to be greater than zero")
    public static class CarErsValueNotValidException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Car consumption value has to be greater or equal to zero")
    public static class CarConsumptionNotValidException extends RuntimeException {}

    // Circuit Exceptions
    @ResponseStatus(code = NOT_FOUND, reason = "Circuit not found")
    public static class CircuitDoesntExistException extends RuntimeException {}

    @ResponseStatus(
            value = UNPROCESSABLE_ENTITY,
            reason =
                    "Needed fields: [name, city, id_country, image, laps, length, slow_turns, medium_turns, fast_turns]")
    public static class CircuitNotSavedException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Circuit name already exists")
    public static class CircuitNameInUseException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Circuit already exists")
    public static class CircuitInUseException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Circuit has to have at least 1 lap")
    public static class CircuitLapsLessThanOneException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Circuit needs to have length >= 0 ")
    public static class CircuitLenghtLessThanZeroException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Circuit needs to have at least 2 turns")
    public static class CircuitTurnsLessThanTwoException extends RuntimeException {}

    // News Exceptions
    @ResponseStatus(code = NOT_FOUND, reason = "News not found")
    public static class NewsDoesntExistException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "News permalink already exists")
    public static class NewsPermalinkInUseException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "News title already exists")
    public static class NewsTitleInUseException extends RuntimeException {}

    @ResponseStatus(
            value = UNPROCESSABLE_ENTITY,
            reason = "Needed fields: [permalink, title, text, publication_date, image (optional)]")
    public static class NewsNotSavedException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "News permalink has to be at least 1 character")
    public static class NewsPermalinkNotValidException extends RuntimeException {}

    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "News title has to be at least 1 character")
    public static class NewsTitleNotValidException extends RuntimeException {}

    // Simulation Exceptions
    @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Simulation not valid, needed fields: [idCar, idCircuit]")
    public static class SimulationNotValidException extends RuntimeException {}

    // Races Exceptions
    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Needed fields: [name, date, sprint, idCircuit]")
    public static class RaceNotSavedException extends RuntimeException {}

    @ResponseStatus(code = NOT_FOUND, reason = "Race not found")
    public static class RaceDoesntExistException extends RuntimeException {}
}
