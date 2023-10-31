package ru.kotb.accounting_system.validation;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * The class that describes unified validation error message.
 */
@Data
@AllArgsConstructor
public class Violation {

    /**
     * The name of the field that has not been validated.
     */
    private String fieldName;

    /**
     * The error validation message.
     */
    private String message;
}
