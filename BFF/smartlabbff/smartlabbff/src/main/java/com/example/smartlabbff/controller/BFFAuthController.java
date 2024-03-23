package com.example.smartlabbff.controller;


import com.example.smartlabbff.dto.Login;
import com.example.smartlabbff.dto.LoginResponse;
import com.example.smartlabbff.dto.UserRequest;
import com.example.smartlabbff.dto.labE;
import com.example.smartlabbff.fiegn.FiegnAdminMs;
import com.example.smartlabbff.fiegn.FiegnAuthMs;
import com.example.smartlabbff.fiegn.FiegnLoginMs;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bff/user")
public class BFFAuthController {

    @Autowired
    private FiegnAuthMs fiegnAuthMs;
    @Autowired
    private FiegnLoginMs fiegnLoginMs;
    @Autowired
    private FiegnAdminMs fiegnAdminMs;


    //Create user
    @PostMapping(value = "/create")
    public ResponseEntity<AccessTokenResponse> createUser(@RequestBody UserRequest user) {

        System.out.printf(String.valueOf(user));

        //Add data into SQL DB
        labE Nlab = new labE();
        Nlab.setEmail(user.getEmail());
        Nlab.setPassword(user.getPassword());
        Nlab.setL_name(user.getUserName());
        Nlab.setL_address(user.getAddress());
        Nlab.setL_phoneN(user.getPhoneNbr());
        fiegnAdminMs.AddLab(Nlab);


        return fiegnAuthMs.createUser(user);

    }


    //User login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login loginRequest) {

        LoginResponse response = new LoginResponse();
        labE labInfo = fiegnLoginMs.LabData(loginRequest.getEmail());
        response.setLab(labInfo);
        System.out.printf(String.valueOf(labInfo));
        response.setAccessTokenResponse(fiegnAuthMs.login(loginRequest).getBody());
        return ResponseEntity.ok(response);
    }

}
