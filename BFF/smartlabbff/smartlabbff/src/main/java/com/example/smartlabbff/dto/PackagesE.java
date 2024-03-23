package com.example.smartlabbff.dto;

import lombok.Data;


@Data
public class PackagesE {

    private Long pid;
    //@Column(name = "p_owner")
    private String powner;
    //@Column(name = "p_destination")
    private String pdestination;
    //@Column(name = "d_id") //deliver
    private String did;
    //@Column(name = "p_status")
    private String pstatus;

}
