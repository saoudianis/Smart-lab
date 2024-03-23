package com.example.smartlabbff.dto;

import lombok.Data;
import org.keycloak.representations.AccessTokenResponse;

@Data
public class LoginResponse {
    private AccessTokenResponse accessTokenResponse;
    private labE lab;
}
