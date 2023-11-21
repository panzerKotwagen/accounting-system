package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.dto.RegistrationDTO;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.impl.AuthenticationService;


/**
 * The class that provides registration a new user and authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;

    private final CommonModelAssembler<User> assembler;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    AuthenticationManager authenticationManager,
                                    CommonModelAssembler<User> assembler) {

        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.assembler = assembler;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body) {
        EntityModel<User> entityModel = assembler.toModel(
                authenticationService.registerUser(body));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody RegistrationDTO body) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.getUsername(), body.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Successfully signed-in.", HttpStatus.OK);
    }
}