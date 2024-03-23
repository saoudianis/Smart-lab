package com.LabTech.AuthMs.Models;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "User")
public class User {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String phoneNbr;
    private UserType userType;

}
