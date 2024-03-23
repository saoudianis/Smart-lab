package com.example.msorderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RoadHistory")
public class RoadHistoryE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RH_id")
    private Long rhid; //RoadHistory identity
    @Column(name = "Deliver_id")
    private String did; //Deliver identity
    @Column(name = "Pack_id")
    private String pid; //Package identity
    @Column(name = "owner_id")
    private String ownerid; //Owner identity
    @Column(name = "RH_date_T")
    private String datet; //Moment that status transfered to trasport
    @Column(name = "RH_date_R")
    private String dater; //Moment that status transfered to reciepted

}
