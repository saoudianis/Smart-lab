package com.LabTech.AuthMs.Dto;

import com.LabTech.AuthMs.Models.UserType;
import lombok.Data;
import org.keycloak.representations.AccessTokenResponse;

@Data
public class loginResponse {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String phoneNbr;
    private UserType userType;
    private AccessTokenResponse acctok;
}
