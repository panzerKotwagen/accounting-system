package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kotb.accounting_system.dto.RegistrationDTO;
import ru.kotb.accounting_system.entity.User;


@RequestMapping("/api/auth")
public interface AuthenticationController {

    @PostMapping("/register")
    User registerUser(@RequestBody RegistrationDTO body);
}
