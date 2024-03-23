package com.example.msorderservice.Dto;

import lombok.Data;

@Data
public class AddNewPackage {
    private Long tid;
    private String pid;

    private String powner;

    private String patient;

    private String page;

    private String dlid;

    private String analyse;
}
