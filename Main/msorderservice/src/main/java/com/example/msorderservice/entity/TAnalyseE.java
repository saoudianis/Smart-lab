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
@Table(name = "TAnalyses")
public class TAnalyseE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tanalyse_id")
    private Long analyseid;
    @Column(name = "t_id")
    private String tid;
    @Column(name = "analyse_id")
    private String analyse;


}
