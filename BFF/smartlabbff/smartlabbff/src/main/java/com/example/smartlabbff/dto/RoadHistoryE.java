package com.example.smartlabbff.dto;

import lombok.Data;

@Data
public class RoadHistoryE {

    private Long rhid; //RoadHistory identity

    private String did; //Deliver identity

    private String pid; //Package identity

    private String ownerid; //Owner identity

    private String datet; //Moment that status transfered to trasport

    private String dater; //Moment that status transfered to reciepted

}
