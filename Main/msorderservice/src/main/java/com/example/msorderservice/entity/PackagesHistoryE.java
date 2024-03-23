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
@Table(name = "PackagesHistory")
public class PackagesHistoryE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ph_id")
    private Long phid;
    private String pid; //package id
    private String powner;//Package Owner
    private String pdistination;  //distination
    private String did; // deliver id
    private String status; // package last status
    private String date; //date

}
