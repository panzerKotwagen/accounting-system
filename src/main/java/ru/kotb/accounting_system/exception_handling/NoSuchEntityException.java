package ru.kotb.accounting_system.exception_handling;


/**
 * The exception for the case when the requested object was not found.
 */
public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String message) {
        super(message);
    }
}
