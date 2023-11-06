package ru.kotb.accounting_system.dto;

import lombok.Data;


@Data
public class RegistrationDTO {

    public String fullName;

    private String username;

    private String password;
}
