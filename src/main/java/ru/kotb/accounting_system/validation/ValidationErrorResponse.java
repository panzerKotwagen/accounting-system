package ru.kotb.accounting_system.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * The class that provides the list of all occurred validation errors.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

    /**
     * The list of all occurred validation errors.
     */
    private List<Violation> violations = new ArrayList<>();
}

