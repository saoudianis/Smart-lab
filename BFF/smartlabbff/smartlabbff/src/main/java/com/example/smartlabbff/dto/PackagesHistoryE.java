package com.example.smartlabbff.dto;

import lombok.Data;

@Data
public class PackagesHistoryE {

    private Long phid;
    private String pid; //package id
    private String powner;//Package Owner
    private String pdistination;  //distination
    private String did; // deliver id
    private String status; // package last status
    private String date; //date
}
