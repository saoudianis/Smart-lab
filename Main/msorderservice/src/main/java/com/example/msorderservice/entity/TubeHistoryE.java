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
@Table(name = "TubeHistory")
public class TubeHistoryE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "th_id")
    private Long thid;
    private String tid; //tube id
    private String pname; //patien name
    private String page; // patien age
    private String distinationlid; // distination
    private String packageid; //pack id
    private String date;

}
