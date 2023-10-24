package ru.kotb.accounting_system.exception_handling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * The exception handler for the entity exceptions.
 */
@ControllerAdvice
public class EntityGlobalExceptionHandler {

    /**
     * Handles the {@code NoSuchEntityException}. Puts the message from
     * the exception in {@code EntityIncorrectData} object and return
     * HTTP response with it.
     *
     * @param exception the {@code NoSuchEntityException} object
     * @return HTTP response with the JSON info message
     */
    @ExceptionHandler
    private ResponseEntity<EntityIncorrectData> handleException(
            NoSuchEntityException exception) {

        EntityIncorrectData data = new EntityIncorrectData(
                exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@code Exception}. Puts the message from the
     * exception in {@code EntityIncorrectData} object and return
     * HTTP response with it.
     *
     * @param exception the {@code Exception} object
     * @return HTTP response with the JSON info message
     */
    @ExceptionHandler
    private ResponseEntity<EntityIncorrectData> handleException(
            Exception exception) {

        EntityIncorrectData data = new EntityIncorrectData(
                exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
