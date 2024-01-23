package ru.kotb.accountingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * The class that provides the list of all occurred validation errors.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponseDTO {

    /**
     * The list of all occurred validation errors.
     */
    private List<ViolationDTO> violations = new ArrayList<>();
}

