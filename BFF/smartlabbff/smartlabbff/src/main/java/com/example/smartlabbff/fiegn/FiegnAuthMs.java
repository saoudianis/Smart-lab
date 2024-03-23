package com.example.smartlabbff.fiegn;

import com.example.smartlabbff.dto.Login;
import com.example.smartlabbff.dto.UserRequest;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = "AuthMs",url = "http://localhost:9099/user")
public interface FiegnAuthMs {

    //Create User
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccessTokenResponse> createUser(@RequestBody UserRequest user);

    //User Lgoin
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccessTokenResponse> login(@RequestBody Login loginRequest);


}
