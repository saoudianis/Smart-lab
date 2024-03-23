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
@Table(name = "LAnalyses")
public class LAnalyseE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "la_id")
    private Long analyseid;
    @Column(name = "lab_id")
    private String tid;
    @Column(name = "analyse_id")
    private String analyse;
}
