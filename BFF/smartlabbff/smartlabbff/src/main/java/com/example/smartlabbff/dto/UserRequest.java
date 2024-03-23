package com.example.smartlabbff.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String userName;
    private String email;
    private String address;
    private String password;
    private String phoneNbr;
    private UserType userType;
}
