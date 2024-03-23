package com.LabTech.AuthMs.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Login {
    String email;
    String password;
}
