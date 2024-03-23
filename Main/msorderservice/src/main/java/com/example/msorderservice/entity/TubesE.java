package com.example.msorderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Tubes")
public class TubesE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long tid;
    @Column(name = "p_id")//Package ID
    private String pid;
    @Column(name = "patient_name")
    private String patient;
    @Column(name = "patient_age")
    private String page;
    @Column(name = "DistinationL_id")
    private String dlid;


}
