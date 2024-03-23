package com.LabTech.AuthMs.Dto;

import com.LabTech.AuthMs.Models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userName;
    private String email;
    private String password;
    private String phoneNbr;
    private UserType userType;
}
