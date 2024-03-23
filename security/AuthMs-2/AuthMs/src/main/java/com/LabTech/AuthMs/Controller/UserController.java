package com.LabTech.AuthMs.Controller;

import com.LabTech.AuthMs.Config.KeycloakProvider;
import com.LabTech.AuthMs.Dto.Login;
import com.LabTech.AuthMs.Dto.UserRequest;
import com.LabTech.AuthMs.Service.KeycloakAdminClientService;
import com.sun.istack.NotNull;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider keycloakProvider;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserController.class);

    public UserController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider, KeycloakProvider keycloakProvider) {
        this.kcAdminClient = kcAdminClient;
        this.keycloakProvider = keycloakProvider;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AccessTokenResponse> createUser(@RequestBody UserRequest user) {
        return   kcAdminClient.createKeycloakUser(user);


    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody Login loginRequest) {
        return    kcAdminClient.loguser(loginRequest);


    }
}
