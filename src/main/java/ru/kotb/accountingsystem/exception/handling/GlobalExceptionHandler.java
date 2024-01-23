package ru.kotb.accountingsystem.exception.handling;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.kotb.accountingsystem.dto.ValidationErrorResponseDTO;
import ru.kotb.accountingsystem.dto.ViolationDTO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


/**
 * The exception handler for the entity exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Processes {@code MethodArgumentNotValidException} which is
     * thrown by bean validation.
     *
     * @param e the handled exception
     * @return the HTTP response with describing the errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponseDTO handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        ValidationErrorResponseDTO error = new ValidationErrorResponseDTO();
        for (FieldError fe : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new ViolationDTO(fe.getField(),
                            fe.getDefaultMessage()));
        }
        return error;
    }

    /**
     * Processes {@code ConstraintViolationException} which is thrown
     * by Spring parameter validation.
     *
     * @param e the handled exception
     * @return the HTTP response with describing the errors
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponseDTO handleConstraintValidationException(
            ConstraintViolationException e) {

        ValidationErrorResponseDTO error = new ValidationErrorResponseDTO();
        for (ConstraintViolation v : e.getConstraintViolations()) {
            error.getViolations().add(
                    new ViolationDTO(v.getPropertyPath().toString(),
                            v.getMessage()));
        }
        return error;
    }

    /**
     * Handles exception that occurred when user try to send not valid
     * query parameters.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    EntityIncorrectData handleNotValidParams(MethodArgumentTypeMismatchException e) {
        return new EntityIncorrectData("Invalid value: " + e.getValue());
    }

    /**
     * Handles the {@code NoSuchEntityException}. Puts the message from
     * the exception in {@code EntityIncorrectData} object and return
     * HTTP response with it.
     *
     * @param exception the {@code NoSuchEntityException} object
     * @return HTTP response with the JSON info message
     */
    @ExceptionHandler(NoSuchEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private EntityIncorrectData handleException(
            NoSuchEntityException exception) {

        return new EntityIncorrectData(exception.getMessage());
    }

    /**
     * Handles the {@code Exception}. Puts the message from the
     * exception in {@code EntityIncorrectData} object and return
     * HTTP response with it.
     *
     * @param exception the {@code Exception} object
     * @return HTTP response with the JSON info message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private EntityIncorrectData handleException(Exception exception) {
        return new EntityIncorrectData(exception.getMessage());
    }
}
